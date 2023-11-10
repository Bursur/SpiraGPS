import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSTheme
import spiragps.data.placeholderRoute
import spiragps.data.Route
import spiragps.data.rememberConditionState
import spiragps.data.rememberContentsState
import spiragps.views.ContentsView
import spiragps.views.HeaderView
import spiragps.views.RouteView

@Composable
internal fun SpiraGPS() {
    val data = Json.decodeFromString<Route>(placeholderRoute)
    val contentsState = rememberContentsState()

    SpiraGPSTheme {
        Row(modifier = Modifier.fillMaxSize()) {
            ContentsView(chapters = data.chapters, contentsState = contentsState, modifier = Modifier.weight(.2f))

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
}
