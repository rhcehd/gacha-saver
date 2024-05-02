package dev.rhcehd123.feature.gacha

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rhcehd123.core.data.repository.GachaDataRepository
import dev.rhcehd123.core.data.repository.GachaSaverRepository
import dev.rhcehd123.core.model.GachaData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GachaViewModel @Inject constructor(
    private val gachaDataRepository: GachaDataRepository,
    private val gachaSaverRepository: GachaSaverRepository,
): ViewModel() {
    private val gachaDataState: StateFlow<GachaData> = gachaDataRepository.gachaData.stateIn(
        scope = viewModelScope,
        initialValue = GachaData.emptyGachaData,
        started = SharingStarted.WhileSubscribed(5_000)
    )
    private val gachaResultState: MutableStateFlow<String> = MutableStateFlow("")

    val gachaUiState: StateFlow<GachaUiState> = gachaDataState.combine(gachaResultState) { gachaData, gachaResult ->
        GachaUiState.Success(gachaData, gachaResult)
    }.stateIn(
        scope = viewModelScope,
        initialValue = GachaUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun onClickGachaButton(userId: String) {
        viewModelScope.launch {
            try {
                val gachaResult = gachaSaverRepository.requestGacha(id = userId, count = 1)
                if(gachaResult.isNotEmpty()) {
                    gachaResultState.update { "YOU GOT THE ${gachaResult.last().name}" }
                } else {
                    // print failure
                    throw Exception("gachaResult is empty")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                gachaResultState.update { "Error..." }
            }
        }
    }
}

sealed interface GachaUiState {
    data object Loading: GachaUiState
    data class Success(
        val gachaData: GachaData,
        val resultText: String = "",
    ): GachaUiState
    data object Failure: GachaUiState
}