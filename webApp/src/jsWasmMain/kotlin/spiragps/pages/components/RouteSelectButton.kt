package spiragps.pages.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.destinations.Destination
import spiragps.data.route.NavigationState
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.style.SpiraGPSVersion

external fun setClipboard(url: String)

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RouteSelectButton(destination: Destination, navigationState: NavigationState) {
    val bgColour = animateColorAsState(SpiraGPSColours.infoBackground)
    val textColour = animateColorAsState(SpiraGPSColours.text)
    val tooltipState = remember { RichTooltipState() }
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Surface(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        color = bgColour.value,
        modifier = Modifier.hoverable(interactionSource)
    ) {
        Box(modifier = Modifier.width(200.dp)) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        navigationState.selectedRouteUrl = destination.data
                        navigationState.currentPage = NavigationState.ROUTE
                    }
            ) {
                Image(
                    painter = if (destination.image.isEmpty())
                        painterResource("SpiraGPS/placeholder-map.jpg")
                    else
                        painterResource(destination.image),
                    contentDescription = ""
                )
            }

            Overlay(
                destination = destination,
                textColour = textColour.value,
                bgColour = bgColour.value,
                modifier = Modifier.align(Alignment.BottomCenter),
                isExpanded = isHovered
            )

            AnimatedVisibility(visible = isHovered, modifier = Modifier.align(Alignment.TopEnd)) {
                RichTooltipBox(
                    text = { Text("Copied to clipboard!", style = SpiraGPSText.typography.info, color = textColour.value) },
                    tooltipState = tooltipState,
                    colors = TooltipDefaults.richTooltipColors(containerColor = bgColour.value),
                ) {
                    Image(
                        painter = painterResource("SpiraGPS/link.png"),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .width(24.dp)
                            .height(24.dp)
                            .background(SpiraGPSColours.fabBackgroundColour, CircleShape)
                            .clickable {
                                setClipboard("https://bursur.github.io/SpiraGPS/?route_id=${destination.id}")
                                scope.launch {
                                    tooltipState.show()
                                }
                            }
                    )
                }
            }
        }
    }

    LaunchedEffect(isHovered) {
        if(!isHovered)
            tooltipState.dismiss()
    }
}

@Composable
private fun Overlay(modifier: Modifier = Modifier, destination: Destination, textColour: Color, bgColour: Color, isExpanded: Boolean) {
    Column(
        modifier = modifier
            .background(bgColour)
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .padding(bottom = 5.dp, start = 5.dp, end = 5.dp)
    ) {
        Text(
            text = destination.name,
            style = SpiraGPSText.typography.info,
            textAlign = TextAlign.Center,
            color = textColour,
            modifier = Modifier.fillMaxWidth()
        )

        if(isExpanded) {
            Text(
                "Author: ${destination.author}",
                style = SpiraGPSText.typography.info,
                color = textColour
            )
        }
    }
}