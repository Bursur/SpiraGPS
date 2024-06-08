package spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSDarkMode
import spiragps.style.SpiraGPSText
import spiragps.views.BulletedList

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ToDoActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }
    val tooltipState = remember { RichTooltipState() }
    val textColour = animateColorAsState(SpiraGPSColours.value.text)
    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    RichTooltipBox(
        text = { Text("ToDo List", style = SpiraGPSText.typography.value.infoBold, color = textColour.value) },
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
                painter = painterResource("SpiraGPS/todo.png"),
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
        ToDoDialog { openAlertDialog = false }
}

@Composable
fun ToDoDialog(onDismissRequest: () -> Unit) {
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
                Column {
                    Text(text = "To Do List", style = SpiraGPSText.typography.value.landingTitle, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text(text = "(In no particular order)", style = SpiraGPSText.typography.value.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
                BulletedList(
                    entry = Entry(
                        guide = arrayListOf(
                            "Routes: Multiple Conditions?",
                            "Routes: GIF Support?",
                            "Routes: YouTube Video Embedding?",
                            "Misc: Credits Page",
                            "Misc: Help Page",
                            "Misc: Version History Page",
                            "Misc: Deep-links",
                            "Routes: BUG! Tables HAVE to have entries totaling a multiple of 2, this shouldn't be the case",
                            "Editor: BUG! Caret is all shy",
                            "Editor: BUG! Images need to be uploaded before they can be seen"
                        )
                    )
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