package spiragps.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.json.Json
import spiragps.data.NavigationState
import spiragps.data.Route
import spiragps.data.rememberConditionState
import spiragps.data.rememberContentsState
import spiragps.style.SpiraGPSText
import spiragps.views.ContentsView
import spiragps.views.HeaderView
import spiragps.views.RouteView

external fun getData(): String

@Composable
fun RoutePage(navigationState: NavigationState) {
    val data = Json.decodeFromString<Route>(getData())
    SpiraGPSText.addKeywords(data.keywords)

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