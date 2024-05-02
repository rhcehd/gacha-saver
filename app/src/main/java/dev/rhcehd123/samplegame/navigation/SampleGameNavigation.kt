package dev.rhcehd123.samplegame.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.rhcehd123.feature.gacha.GachaScreen
import dev.rhcehd123.feature.setting.SettingsScreen
import dev.rhcehd123.samplegame.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SampleGameScreens {
    const val Gacha = "Gacha"
    const val Settings = "Settings"
}

fun NavGraphBuilder.sampleGameNavigation(
    userId: String,
    onShowSnackbar: suspend (String, String?) -> Unit,
) {
    composable(route = SampleGameScreens.Gacha) {
        GachaScreen(
            userId = userId
        )
    }

    composable(route = SampleGameScreens.Settings) {
        SettingsScreen(
            onShowSnackbar = onShowSnackbar,
        )
    }
}

@Composable
fun SampleGameDrawerContent(
    navHostController: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    ModalDrawerSheet {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = stringResource(id = R.string.app_name),
        )
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(text = SampleGameScreens.Settings) },
            selected = false,
            onClick = {
                if(navHostController.currentBackStackEntry?.destination?.route != SampleGameScreens.Settings) {
                    navHostController.navigate(SampleGameScreens.Settings)
                }
                scope.launch {
                    if(drawerState.isOpen) {
                        drawerState.close()
                    }
                }
            }
        )
    }
}