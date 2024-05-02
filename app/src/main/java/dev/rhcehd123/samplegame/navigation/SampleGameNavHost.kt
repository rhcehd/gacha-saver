package dev.rhcehd123.samplegame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SampleGameNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    userId: String,
    onShowSnackbar: suspend (String, String?) -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = SampleGameScreens.Gacha,
    ) {
        sampleGameNavigation(
            userId = userId,
            onShowSnackbar = onShowSnackbar
        )
    }
}