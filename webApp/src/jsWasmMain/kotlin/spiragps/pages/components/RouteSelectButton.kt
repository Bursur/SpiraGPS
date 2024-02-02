package spiragps.pages.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.destinations.Destination
import spiragps.data.route.NavigationState
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RouteSelectButton(destination: Destination, navigationState: NavigationState) {
    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Surface(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        color = bgColour.value,
    ) {
        Column(
            modifier = Modifier
                .width(200.dp)
                .clickable {
                    navigationState.selectedRouteUrl = destination.data
                    navigationState.currentPage = NavigationState.ROUTE
                }
        ) {
            Image(
                painter = if(destination.image.isEmpty()) painterResource("SpiraGPS/placeholder-map.jpg") else painterResource(destination.image),
                contentDescription = ""
            )

            Text(
                text = destination.name,
                style = SpiraGPSText.typography.value.info,
                textAlign = TextAlign.Center,
                color = textColour.value,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}