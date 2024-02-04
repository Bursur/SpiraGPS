package spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.BulletedList

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ToDoActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.value.fabBackgroundColour,
        contentColor = SpiraGPSColours.value.fabIconColour,
        modifier = modifier
    ) {
        Image(painter = painterResource("SpiraGPS/todo.png"), contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
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
                            "Editor: Conditions",
                            "Editor: Enable Pasting text",
                            "Routes: Multiple Conditions Handling",
                            "Routes: GIF Support?",
                            "Routes: YouTube Video Embedding?",
                            "Misc: Credits Page",
                            "Misc: Help Page",
                            "Misc: Version History Page",
                            "Routes: BUG! Tables HAVE to have entries totaling a multiple of 2, this shouldn't be the case",
                            "Editor: BUG! Caret is all shy"
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