import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.DeeplinkService
import spiragps.data.destinations.SpiraDestinations
import spiragps.data.route.NavigationState
import spiragps.data.route.rememberNavigationState
import spiragps.dialogs.AboutActionButton
import spiragps.dialogs.SettingsActionButton
import spiragps.dialogs.ToDoActionButton
import spiragps.pages.EditorPage
import spiragps.pages.LandingPage
import spiragps.pages.LoadingPage
import spiragps.style.SpiraGPSTheme
import spiragps.pages.RoutePage
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
internal fun SpiraGPS() {
    SpiraGPSTheme {
        var loading by remember { mutableStateOf(true) }
        val navigationState = rememberNavigationState()
        val bgColour = animateColorAsState(SpiraGPSColours.value.background)

        if(!loading) {
            Box(modifier = Modifier.fillMaxSize().background(bgColour.value)) {
                when (navigationState.currentPage) {
                    NavigationState.LANDING -> LandingPage(navigationState)
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
            SpiraGPSText.loadFonts()
            SpiraDestinations.loadDestinations()

            DeeplinkService.followRouteLink(navigationState)
            if(navigationState.currentPage == NavigationState.LANDING)
                DeeplinkService.followEditorLink(navigationState)

            loading = false
        }
    }
}
