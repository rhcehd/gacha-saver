package dev.rhcehd123.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rhcehd123.core.designsystem.R
import dev.rhcehd123.designsystem.theme.SampleGameTheme

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
