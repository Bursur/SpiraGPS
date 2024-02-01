import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import spiragps.data.destinations.SpiraDestinations
import spiragps.data.route.NavigationState
import spiragps.data.route.rememberNavigationState
import spiragps.dialogs.HelpDialog
import spiragps.pages.EditorPage
import spiragps.pages.LandingPage
import spiragps.pages.LoadingPage
import spiragps.style.SpiraGPSTheme
import spiragps.pages.RoutePage
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.HelpActionButton


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

                HelpActionButton(modifier = Modifier.align(Alignment.BottomEnd))
            }
        }
        else
            LoadingPage()

        LaunchedEffect(Unit) {
            SpiraGPSText.loadFonts()
            SpiraDestinations.loadDestinations()
            loading = false
        }
    }
}
