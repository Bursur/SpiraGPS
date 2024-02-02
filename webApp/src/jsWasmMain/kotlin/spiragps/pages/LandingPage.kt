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
import spiragps.pages.components.EditorSelectButton
import spiragps.pages.components.LocalRouteSelectButton
import spiragps.pages.components.RouteSelectButton
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.style.darkScheme
import spiragps.style.lightScheme
import spiragps.views.BulletedList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LandingPage(navigationState: NavigationState) {
    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Spira GPS",
            style = SpiraGPSText.typography.value.landingTitle,
            textAlign = TextAlign.Center,
            color = textColour.value,
            modifier = Modifier.padding(top = 50.dp, bottom = 10.dp)
        )

        Text(
            text = "Select your destination",
            style = SpiraGPSText.typography.value.info,
            textAlign = TextAlign.Center,
            color = textColour.value
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

            //LocalRouteSelectButton(navigationState)
            EditorSelectButton(navigationState)
        }

        // TODO LIST REMOVE THIS!!
        Divider(
            color = bgColour.value,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        BulletedList(
            entry = Entry(
                text = "ToDo: (In no particular order)",
                guide = arrayListOf(
                    "Editor: Load and Save",
                    "Editor: Drag and Drop",
                    "Editor: Conditions",
                    "Editor: Make the entries editable",
                    "Editor: Make it pretty",
                    "Editor: Enable Pasting text",
                    "Routes: Load Local Route json file. If possible",
                    "Routes: Multiple Conditions Handling",
                    "Routes: GIF Support?",
                    "Routes: YouTube Video Embedding?",
                    "Misc: Credits Page",
                    "Misc: Help Page",
                    "Misc: ToDo List Page",
                    "Misc: Version History Page",
                    "Routes: BUG! Tables HAVE to have entries totaling a multiple of 2, this shouldn't be the case",
                    "Editor: BUG! Caret is all shy",
                    "Misc: BUG! Arrows don't show on Dyslexic Fonts"
                )
            )
        )
    }
}