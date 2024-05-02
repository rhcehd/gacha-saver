package dev.rhcehd123.feature.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.rhcehd123.core.model.GachaData
import dev.rhcehd123.core.model.GachaItem
import dev.rhcehd123.designsystem.theme.SampleGameTheme
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onShowSnackbar: suspend (String, String?) -> Unit,
) {
    val uiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    val snackbarState by viewModel.snackbarState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onChangeRate = viewModel::onChangeRate,
        onResetData = viewModel::onResetData,
    )

    SnackbarEvent(
        snackbarState = snackbarState,
        afterSnackbarEvent = viewModel::afterSnackbarEvent,
        onShowSnackbar = onShowSnackbar,
    )
}

@Composable
private fun SnackbarEvent(
    snackbarState: SnackbarState,
    afterSnackbarEvent: () -> Unit,
    onShowSnackbar: suspend (String, String?) -> Unit,
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        when(snackbarState) {
            is SnackbarState.None -> {}
            is SnackbarState.InvalidValue -> {
                onShowSnackbar("Invalid Value", null)
                afterSnackbarEvent()
            }
            is SnackbarState.UndefinedError -> {
                onShowSnackbar("Undefined Error", null)
                afterSnackbarEvent()
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    uiState: SettingsUiState,
    onChangeRate: (String, String, String) -> Unit,
    onResetData: () -> Unit,
) {
    when(uiState) {
        is SettingsUiState.Loading -> {}
        is SettingsUiState.Success -> SuccessState(
            uiState = uiState,
            onChangeRate = onChangeRate,
            onResetData = onResetData,
        )
    }

}

private const val LIST_ITEM_VERTICAL_PADDING = 16
private const val LIST_ITEM_HORIZONTAL_PADDING = 8
@Composable
private fun SuccessState(
    uiState: SettingsUiState.Success,
    onChangeRate: (String, String, String) -> Unit,
    onResetData: () -> Unit,
) {
    var showCheckResetDataDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LIST_ITEM_VERTICAL_PADDING.dp,
                    horizontal = LIST_ITEM_HORIZONTAL_PADDING.dp
                )
        ) {
            Text(
                text = "Change Rate"
            )
            Text(
                text = "Click item to Change Rate"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GachaTable(
                    gachaData = uiState.gachaData,
                    onChangeRate = onChangeRate,
                )
            }
        }

        HorizontalDivider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showCheckResetDataDialog = true
                }
                .padding(
                    vertical = LIST_ITEM_VERTICAL_PADDING.dp,
                    horizontal = LIST_ITEM_HORIZONTAL_PADDING.dp
                )
        ) {
            Text(
                text = "Reset Data"
            )
            Text(text = "Reset to default data")
        }

        HorizontalDivider()
    }

    if(showCheckResetDataDialog) {
        CheckResetDataDialog(
            onDismiss = { showCheckResetDataDialog = false },
            onResetData = onResetData
        )
    }
}

@Composable
private fun GachaTable(
    gachaData: GachaData,
    onChangeRate: (String, String, String) -> Unit,
) {
    var selectedItem by rememberSaveable {
        mutableStateOf<GachaItem?>(null)
    }

    LazyColumn(
        modifier = Modifier
            .width(240.dp)
    ) {
        gachaData.groupList.forEach { group ->
            item {
                Row(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .fillParentMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = group.name
                    )
                    Text(
                        text = "${group.groupRate}%",
                    )
                }
            }
            items(
                group.itemList
            ) { item ->
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable { selectedItem = item },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name
                    )
                    Text(
                        text = "${item.rate}%"
                    )
                }
            }
        }
    }

    if(selectedItem != null) {
        ChangeRateDialog(
            selectedItem = selectedItem!!,
            onChangeRate = onChangeRate,
            onDismiss = {
                selectedItem = null
            },
        )
    }
}

@Composable
fun ChangeRateDialog(
    selectedItem: GachaItem,
    onChangeRate: (String, String, String) -> Unit,
    onDismiss: () -> Unit,
) {
    var newRate by rememberSaveable {
        mutableStateOf(selectedItem.rate)
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Change the rate of ${selectedItem.name}"
            )
        },
        text = {
            BasicTextField(
                value = newRate,
                onValueChange = { newValue: String ->
                    newRate = newValue
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        },
        confirmButton = {
            Text(
                modifier = Modifier
                    .clickable {
                        onChangeRate(selectedItem.groupName, selectedItem.name, newRate)
                        onDismiss()
                    },
                text = "APPLY",
            )
        },                                   
    )
}

@Composable
fun CheckResetDataDialog(
    onDismiss: () -> Unit,
    onResetData: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Text(
                modifier = Modifier
                    .clickable {
                        onResetData()
                        onDismiss()
                    },
                text = "CONFIRM"
            )
        }
    )
}

@Composable
@Preview(showSystemUi = true, apiLevel = 33)
private fun SettingsScreenPreview() {
    SampleGameTheme {
        SettingsScreen(
            SettingsUiState.Success(GachaData.defaultGachaData),
            onChangeRate = {_, _, _ ->},
            onResetData = {},
        )
    }
}