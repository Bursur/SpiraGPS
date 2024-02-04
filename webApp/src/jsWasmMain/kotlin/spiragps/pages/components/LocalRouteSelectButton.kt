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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.route.NavigationState
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import spiragps.data.FileService

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LocalRouteSelectButton(navigationState: NavigationState) {
    val scope = rememberCoroutineScope()
    var awaitingData by remember { mutableStateOf(false) }
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
                    scope.launch {
                        awaitingData = true
                    }
                }
        ) {
            Image(
                painter = painterResource("SpiraGPS/placeholder-map.jpg"),
                contentDescription = ""
            )

            Text(
                text = "Load Route File",
                style = SpiraGPSText.typography.value.info,
                textAlign = TextAlign.Center,
                color = textColour.value,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    LaunchedEffect(awaitingData) {
        if(awaitingData) {
            val data = FileService.loadFile()

            awaitingData = false

            if(data != "cancelled") {
                navigationState.data = data
                navigationState.currentPage = NavigationState.ROUTE
            }
        }
    }
}