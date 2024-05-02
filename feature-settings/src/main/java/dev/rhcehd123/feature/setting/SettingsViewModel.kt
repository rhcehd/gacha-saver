package dev.rhcehd123.feature.setting

import android.icu.math.BigDecimal
import android.icu.text.DecimalFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rhcehd123.core.data.repository.GachaDataRepository
import dev.rhcehd123.core.model.GachaData
import dev.rhcehd123.core.model.updateGroup
import dev.rhcehd123.core.model.updateItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val gachaDataRepository: GachaDataRepository
): ViewModel() {
    val gachaDataState: StateFlow<GachaData> = gachaDataRepository.gachaData.stateIn(
        scope = viewModelScope,
        initialValue = GachaData.emptyGachaData,
        started = SharingStarted.WhileSubscribed(5_000)
    )
    val snackbarState: MutableStateFlow<SnackbarState> = MutableStateFlow(SnackbarState.None)
    val settingsUiState: StateFlow<SettingsUiState> = gachaDataState
        .map { SettingsUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = SettingsUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    fun onChangeRate(groupName: String, itemName: String, newRate: String) {
        try {
            newRate.toDouble()
        } catch (e: NumberFormatException) {
            snackbarState.update { SnackbarState.InvalidValue }
            return
        }
        val gachaData = gachaDataState.value
        val targetGroup = gachaData.groupList.find { it.name == groupName } ?: run {
            snackbarState.update { SnackbarState.UndefinedError }
            return
        }
        val groupRateDecimal = BigDecimal(targetGroup.groupRate)
        val newRateDecimal = BigDecimal(newRate)
        val otherRateDecimal = groupRateDecimal
            .subtract(newRateDecimal)
            .divide(
                BigDecimal(targetGroup.itemList.size - 1), 3
            )

        val newItemList = targetGroup.itemList.map { gachaItem ->
            if(gachaItem.name == itemName) {
                gachaItem.copy(rate = newRateDecimal.formatRate())
            } else {
                gachaItem.copy(rate = otherRateDecimal.formatRate())
            }
        }
        val newGroup = targetGroup.updateItem(newItemList)
        val newGachaData = gachaData.updateGroup(listOf(newGroup))

        viewModelScope.launch {
            gachaDataRepository.updateGachaData(newGachaData)
        }
    }

    fun onResetData() {
        viewModelScope.launch {
            gachaDataRepository.resetData()
        }
    }

    fun afterSnackbarEvent() {
        snackbarState.update { SnackbarState.None }
    }
}

private val formatter = DecimalFormat("#.000")
private fun BigDecimal.formatRate(): String {
    return formatter.format(this)
}

sealed interface SettingsUiState {
    data object Loading: SettingsUiState
    data class Success(val gachaData: GachaData): SettingsUiState
}

sealed interface SnackbarState {
    data object None: SnackbarState
    data object InvalidValue: SnackbarState
    data object UndefinedError: SnackbarState
}