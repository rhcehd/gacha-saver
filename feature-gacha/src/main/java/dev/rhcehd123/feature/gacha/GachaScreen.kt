package dev.rhcehd123.feature.gacha

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.rhcehd123.core.designsystem.component.SampleGameLoadingWheel
import dev.rhcehd123.core.model.GachaData
import dev.rhcehd123.designsystem.theme.SampleGameTheme

@Composable
fun GachaScreen(
    viewModel: GachaViewModel = hiltViewModel(),
    userId: String,
) {
    val uiState by viewModel.gachaUiState.collectAsStateWithLifecycle()
    GachaScreen(
        uiState = uiState,
        userId = userId,
        onClickGachaButton = viewModel::onClickGachaButton
    )
}

@Composable
private fun GachaScreen(
    uiState: GachaUiState,
    userId: String,
    onClickGachaButton: (String) -> Unit
) {
    when(uiState) {
        is GachaUiState.Loading -> LoadingState()
        is GachaUiState.Success -> SuccessState(
            uiState = uiState,
            userId = userId,
            onClickGachaButton = onClickGachaButton
        )
        is GachaUiState.Failure -> FailureState()
    }
}

@Composable
private fun LoadingState() {
    SampleGameLoadingWheel(contentDesc = "")
}

@Composable
private fun SuccessState(
    modifier: Modifier = Modifier,
    uiState: GachaUiState.Success,
    userId: String,
    onClickGachaButton: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GachaTable(
            gachaData = uiState.gachaData
        )
        Text(
            modifier = Modifier.padding(
                vertical = 16.dp
            ),
            text = uiState.resultText
        )
        Button(
            modifier = Modifier.padding(
                vertical = 16.dp
            ),
            onClick = { onClickGachaButton(userId) }
        ) {
            Text(text = "GACHA")
        }
    }
}

@Composable
private fun FailureState() {

}

@Composable
private fun GachaTable(
    gachaData: GachaData
) {
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
                        text = "${group.groupRate}%"
                    )
                }
            }
            items(
                group.itemList
            ) { item ->
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth(),
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
}

@Preview(showSystemUi = true, apiLevel = 33)
@Composable
fun GachaScreenLoadingStatePreview() {
    SampleGameTheme {
        LoadingState()
    }
}

@Preview(showSystemUi = true, apiLevel = 33)
@Composable
fun GachaScreenPreview() {
    val uiState = GachaUiState.Success(
        gachaData = GachaData.defaultGachaData
    )
    SampleGameTheme {
        GachaScreen(
            uiState = uiState,
            userId = "",
            onClickGachaButton = {_ ->},
        )
    }
}

@Preview(showSystemUi = true, apiLevel = 33)
@Composable
fun GachaScreenFailureStatePreview() {
    SampleGameTheme {
        FailureState()
    }
}