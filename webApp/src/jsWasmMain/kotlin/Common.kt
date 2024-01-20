import androidx.compose.runtime.*
import spiragps.data.destinations.SpiraDestinations
import spiragps.data.route.NavigationState
import spiragps.data.route.rememberNavigationState
import spiragps.pages.EditorPage
import spiragps.pages.LandingPage
import spiragps.pages.LoadingPage
import spiragps.style.SpiraGPSTheme
import spiragps.pages.RoutePage
import spiragps.style.SpiraGPSText


@Composable
internal fun SpiraGPS() {
    SpiraGPSTheme {
        var loading by remember { mutableStateOf(true) }
        val navigationState = rememberNavigationState()

        if(!loading) {
            when(navigationState.currentPage) {
                NavigationState.LANDING -> LandingPage(navigationState)
                NavigationState.ROUTE -> RoutePage(navigationState)
                NavigationState.EDITOR -> EditorPage(navigationState)
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
