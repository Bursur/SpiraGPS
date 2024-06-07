package spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.pages.components.SpiraGPSTitle
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSDarkMode
import spiragps.style.SpiraGPSText
import spiragps.style.SpiraGPSVersion

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AboutActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }
    val tooltipState = remember { RichTooltipState() }
    val textColour = animateColorAsState(SpiraGPSColours.value.text)
    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    RichTooltipBox(
        title = { Text("Info", style = SpiraGPSText.typography.value.infoBold, color = textColour.value) },
        text = { Text("v$SpiraGPSVersion", style = SpiraGPSText.typography.value.info, color = textColour.value) },
        tooltipState = tooltipState,
        colors = TooltipDefaults.richTooltipColors(containerColor = bgColour.value),
    ) {
        SmallFloatingActionButton(
            onClick = {
                openAlertDialog = true
            },
            containerColor = SpiraGPSColours.value.fabBackgroundColour,
            contentColor = SpiraGPSColours.value.fabIconColour,
            modifier = modifier.tooltipAnchor().hoverable(interactionSource)
        ) {
            Image(
                painter = painterResource("SpiraGPS/info.png"),
                contentDescription = null,
                modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp)
            )
        }
    }

    LaunchedEffect(isHovered) {
        if(isHovered)
            tooltipState.show()
        else
            tooltipState.dismiss()
    }

    if(openAlertDialog)
        AboutDialog { openAlertDialog = false }
}

@Composable
fun AboutDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        val textColour = animateColorAsState(SpiraGPSColours.value.text)
        val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)

        Card(colors = CardDefaults.cardColors(containerColor = bgColour.value, contentColor = bgColour.value)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(500.dp)
                    .padding(10.dp)
            ) {
                SpiraGPSTitle()
                Text(
                    text = "The intention of this tool is to gather routes in one place. " +
                        "It also provides a way to edit new or existing runs with a visual editor.\n\n" +
                        "To get started select a route from the landing page, from there you can " +
                        "jump around the run using the contents panel. Clicking the title of each " +
                        "section or panel will expand and contract it, toggling the options at the " +
                        "top of the screen will update the route on the fly without needing to reload " +
                        "the document",
                    style = SpiraGPSText.typography.value.info, color = textColour.value
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Close", style = SpiraGPSText.typography.value.info, color = textColour.value)
                }
            }
        }
    }
}