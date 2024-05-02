package dev.rhcehd123.samplegame.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dev.rhcehd123.designsystem.component.SampleGameDrawer
import dev.rhcehd123.designsystem.component.SampleGameTopBar
import dev.rhcehd123.samplegame.navigation.SampleGameDrawerContent
import dev.rhcehd123.samplegame.navigation.SampleGameNavHost
import kotlinx.coroutines.launch

@Composable
fun SampleGameMain(
    userId: String,
) {
    val navHostController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    SampleGameDrawer(
        drawerState = drawerState,
        drawerContent = {
            SampleGameDrawerContent(
                navHostController = navHostController,
                drawerState = drawerState,
                scope = coroutineScope,
            )
        }
    ) {
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            topBar = {
                SampleGameTopBar(
                    onClickNavigation = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            }
        ) { paddingValues ->
            SampleGameNavHost(
                modifier = Modifier
                    .padding(paddingValues),
                navHostController = navHostController,
                userId = userId,
                onShowSnackbar = { message: String, action: String? ->
                    snackbarHostState.showSnackbar(
                        message = message,
                        //actionLabel = action,
                        duration = SnackbarDuration.Short,
                    )
                }
            )
        }
    }
}