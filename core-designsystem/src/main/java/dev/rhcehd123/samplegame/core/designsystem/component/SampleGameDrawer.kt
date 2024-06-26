package dev.rhcehd123.samplegame.core.designsystem.component

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

@Composable
fun SampleGameDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            drawerContent.invoke()
        }
    ) {
        content.invoke()
    }
}

/*
@Preview(apiLevel = 33)
@Composable
private fun SampleGameDrawerPreview() {
    val drawerOpenState = DrawerState(DrawerValue.Open)
    SampleGameTheme {
        SampleGameDrawer(
            drawerState = DrawerState(DrawerValue.Open),
            drawerContent = {  }) {

        }
    }
}*/
