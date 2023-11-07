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
            Text(text = "This is VERY WIP route for Boosters%. Full credits to Mtbanger for these notes. The notes are Mtbangers notes.", modifier = Modifier.fillMaxWidth(.5f))
            Text(text = "Credits from Mt's notes: Credit to psychonauter, MorphaSRDC, and ChrisTenarium for original Booster% notes and route, MrTyton for the Zanarkand Trials map, to CrimsonInferno and the FFX Blitzball Haters Club HQ Big Nerds for helping me make this bad idea almost good.", modifier = Modifier.fillMaxWidth(.5f))

            Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))

            RouteView(data)
        }
    }
}
