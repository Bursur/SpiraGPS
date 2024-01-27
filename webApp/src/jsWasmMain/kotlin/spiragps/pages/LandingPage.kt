package spiragps.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import spiragps.views.BulletedList

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
                RouteSelectButton(destination = it, navigationState = navigationState)
            }

            //LocalRouteSelectButton(navigationState)
            EditorSelectButton(navigationState)
        }

        // TODO LIST REMOVE THIS!!
        Divider(
            color = SpiraGPSColours.infoBackground,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        BulletedList(
            entry = Entry(
                text = "ToDo: (In no particular order)",
                guide = arrayListOf(
                    "Editor: Load and Save",
                    "Editor: Drag and Drop",
                    "Editor: Chapters",
                    "Editor: Panels",
                    "Editor: Conditions",
                    "Editor: Make it pretty",
                    "Routes: Load Local Route json file. if possible",
                    "Routes: Multiple Conditions",
                    "Misc: Credits Page",
                    "Misc: Help Page",
                    "Misc: ToDo List Page",
                    "Routes: BUG! Tables HAVE to have entries totaling a multiple of 2, this shouldn't be the case",
                    "Routes: BUG! Update the contents page to use indices as using chapter names causes conflicts with ambiguous chapter names",
                    "Editor: BUG! Table items are not editable",
                    "Editor: BUG! Bullet items are not editable"
                )
            )
        )
    }
}