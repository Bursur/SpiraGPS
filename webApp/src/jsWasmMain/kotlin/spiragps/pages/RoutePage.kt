package spiragps.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import spiragps.data.route.NavigationState
import spiragps.data.route.Route
import spiragps.data.route.rememberConditionState
import spiragps.data.route.rememberContentsState
import spiragps.style.SpiraGPSText
import spiragps.utils.loadResource
import spiragps.views.ContentsView
import spiragps.views.HeaderView
import spiragps.views.LoadingView
import spiragps.views.RouteView

@Composable
fun RoutePage(navigationState: NavigationState) {
    var loading by remember { mutableStateOf(true) }
    var data: Route by remember { mutableStateOf(Route()) }

    if(!loading) {
        val contentsState = rememberContentsState()
        Row(modifier = Modifier.fillMaxSize()) {
            ContentsView(
                chapters = data.chapters,
                contentsState = contentsState,
                navigationState = navigationState,
                modifier = Modifier.weight(.2f)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                val conditionState = rememberConditionState(data.conditions)

                // Header
                HeaderView(data.conditions, conditionState)

                // Contents/Route
                RouteView(data, conditionState, contentsState)
            }
        }
    }
    else
        LoadingView("Calculating Route...")

    LaunchedEffect(Unit) {
        if(navigationState.selectedRouteUrl.isNotEmpty()) {
            val jsonString = loadResource("${navigationState.selectedRouteUrl}?cb=${Clock.System.now()}").decodeToString()
            Json.decodeFromString<Route>(jsonString).let {
                SpiraGPSText.addKeywords(it.keywords)
                data = it
            }
        }
        else {
            Json.decodeFromString<Route>(navigationState.data).let {
                SpiraGPSText.addKeywords(it.keywords)
                data = it
            }
        }

        navigationState.data = ""
        navigationState.selectedRouteUrl = ""

        loading = false
    }
}