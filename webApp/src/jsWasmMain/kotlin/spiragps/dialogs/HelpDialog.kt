package spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun HelpActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.value.fabBackgroundColour,
        contentColor = SpiraGPSColours.value.fabIconColour,
        modifier = modifier
    ) {
        Text(text = "?", style = SpiraGPSText.typography.value.infoBold)
    }

    if(openAlertDialog)
        HelpDialog { openAlertDialog = false }
}

@Composable
fun HelpDialog(onDismissRequest: () -> Unit) {
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
                Row {
                    Text(text = "Spira GPS", style = SpiraGPSText.typography.value.landingTitle, color = textColour.value)
                    Text(text = "(v0.2.0)", style = SpiraGPSText.typography.value.info, color = textColour.value)
                }
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