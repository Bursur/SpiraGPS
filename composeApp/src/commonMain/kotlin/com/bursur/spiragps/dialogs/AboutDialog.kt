package com.bursur.spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.title.SpiraGPSTitle
import com.seiko.imageloader.rememberImagePainter

@Composable
fun AboutActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.fabBackgroundColour,
        contentColor = SpiraGPSColours.fabIconColour,
        modifier = modifier.hoverable(interactionSource)
    ) {
        Image(
            rememberImagePainter("https://bursur.github.io/SpiraGPS/info.png"),
            contentDescription = null,
            modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp)
        )
    }

    if(openAlertDialog)
        AboutDialog { openAlertDialog = false }
}

@Composable
fun AboutDialog(onDismissRequest: () -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        val textColour by animateColorAsState(SpiraGPSColours.text)
        val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)

        Card(colors = CardDefaults.cardColors(containerColor = bgColour, contentColor = bgColour)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(700.dp)
                    .padding(15.dp)
            ) {
                SpiraGPSTitle()
                Text(
                    text = "This tool provides a way to edit new or existing notes with a visual WYSIWYG editor.\n\n" +
                            "To get started select a route from the landing page, from there you can " +
                            "jump around the run using the contents list on the left. Clicking the title of each " +
                            "chapter or panel will expand and collapse it, toggling the options at the " +
                            "top of the screen will update the route on the fly without needing to reload " +
                            "the document.\n\n" +
                            "The editor section allows you to build up a route, complete with custom keywords and " +
                            "conditional values, and view it as a user would see it when they load it up. There's a " +
                            "(hopefully!) helpful guide to help you through creating a new set of notes.",
                    style = SpiraGPSText.typography.info, color = textColour
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Close", style = SpiraGPSText.typography.info, color = textColour)
                }
            }
        }
    }
}