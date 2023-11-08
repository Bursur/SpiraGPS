import androidx.compose.foundation.layout.Column
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
import spiragps.views.HeaderView
import spiragps.views.RouteView

@Composable
internal fun SpiraGPS() {
    val data = Json.decodeFromString<Route>(placeholderRoute)

    SpiraGPSTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Header
            HeaderView(data.conditions)

            // Contents/Route
            RouteView(data)
        }
    }
}
