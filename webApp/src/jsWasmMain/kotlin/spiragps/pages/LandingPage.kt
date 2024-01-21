package spiragps.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.destinations.SpiraDestinations
import spiragps.data.route.NavigationState
import spiragps.pages.components.LocalRouteSelectButton
import spiragps.pages.components.RouteSelectButton
import spiragps.style.SpiraGPSText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LandingPage(navigationState: NavigationState) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Spira GPS",
            fontFamily = SpiraGPSText.fontFamily,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 50.dp, bottom = 10.dp)
        )

        Text(
            text = "Select your destination",
            fontFamily = SpiraGPSText.fontFamily,
            textAlign = TextAlign.Center
        )

        FlowRow(
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            modifier = Modifier.padding(top = 25.dp)
        ) {
            SpiraDestinations.list.destinations.forEach {
                RouteSelectButton(routeName = it.name, dataUrl = it.data, navigationState = navigationState)
            }

            LocalRouteSelectButton(navigationState)
        }
    }
}