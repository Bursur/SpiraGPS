package com.bursur.spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.seiko.imageloader.rememberImagePainter

@Composable
fun ToDoActionButton(modifier: Modifier = Modifier) {
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
        Icon(
            imageVector = Icons.Outlined.Checklist,
            contentDescription = null,
            modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp)
        )
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
        val textColour by animateColorAsState(SpiraGPSColours.text)
        val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)

        Card(colors = CardDefaults.cardColors(containerColor = bgColour, contentColor = bgColour)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(500.dp)
                    .padding(10.dp)
            ) {
                Column {
                    Text(text = "To Do List", style = SpiraGPSText.typography.landingTitle, color = textColour, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text(text = "(In no particular order)", style = SpiraGPSText.typography.info, color = textColour, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
                BulletedList(
                    entry = Entry(
                        guide = arrayListOf(
                            "Routes: GIF Support?",
                            "Routes: Links",
                            "Misc: Version History Page (Since you've been gone page?)",
                            "Routes: BUG! Tables HAVE to have entries totaling a multiple of 2, this shouldn't be the case",
                            "Editor: Chapters cannot be re-organised"
                        )
                    )
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