package spiragps.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.destinations.SpiraDestinations
import spiragps.data.route.Entry
import spiragps.data.route.NavigationState
import spiragps.pages.components.EditorHelpButton
import spiragps.pages.components.EditorSelectButton
import spiragps.pages.components.LocalRouteSelectButton
import spiragps.pages.components.RouteSelectButton
import spiragps.pages.components.SpiraGPSTitle
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.style.darkScheme
import spiragps.style.lightScheme
import spiragps.views.BulletedList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LandingPage(navigationState: NavigationState) {
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SpiraGPSTitle()

        Text(
            text = "Select your destination",
            style = SpiraGPSText.typography.value.info,
            textAlign = TextAlign.Center,
            color = textColour.value,
            modifier = Modifier.padding(top = 10.dp)
        )

        FlowRow(
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            modifier = Modifier.padding(top = 25.dp)
        ) {
            SpiraDestinations.list.destinations.forEach {
                RouteSelectButton(destination = it, navigationState = navigationState)
            }

            LocalRouteSelectButton(navigationState)
            EditorSelectButton(navigationState)
            EditorHelpButton(navigationState)
        }
    }
}