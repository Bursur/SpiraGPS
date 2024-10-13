package com.bursur.spiragps

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.deeplinks.followEditorLink
import com.bursur.spiragps.deeplinks.followRouteLink
import com.bursur.spiragps.destinations.SpiraDestinations
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.navigation.rememberNavigationState
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.SpiraGPSTheme
import com.bursur.spiragps.dialogs.AboutActionButton
import com.bursur.spiragps.dialogs.SettingsActionButton
import com.bursur.spiragps.dialogs.ToDoActionButton
import com.bursur.spiragps.editor.EditorPage
import com.bursur.spiragps.home.HomePage
import com.bursur.spiragps.loading.LoadingPage
import com.bursur.spiragps.route.RoutePage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SpiraGPS() {
    SpiraGPSTheme {
        var loading by remember { mutableStateOf(true) }
        val navigationState = rememberNavigationState()
        val bgColour = animateColorAsState(SpiraGPSColours.background)

        if(!loading) {
            Box(modifier = Modifier.fillMaxSize().background(bgColour.value)) {
                when (navigationState.currentPage) {
                    NavigationState.HOME -> HomePage(navigationState)
                    NavigationState.ROUTE -> RoutePage(navigationState)
                    NavigationState.EDITOR -> EditorPage(navigationState)
                }

                Row(modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)) {
                    AboutActionButton()
                    SettingsActionButton()
                    ToDoActionButton()
                }
            }
        }
        else
            LoadingPage()

        LaunchedEffect(Unit) {
            launch(Dispatchers.Default) {
                SpiraGPSText.loadFonts()
                SpiraDestinations.loadDestinations()

                followRouteLink(navigationState)
                if (navigationState.currentPage == NavigationState.HOME)
                    followEditorLink(navigationState)

                loading = false
            }
        }
    }
}