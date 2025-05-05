package com.bursur.spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSDarkMode
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
        Icon(
            imageVector = Icons.Outlined.Info,
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
        val localUriHandler = LocalUriHandler.current

        Card(colors = CardDefaults.cardColors(containerColor = bgColour, contentColor = bgColour)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(700.dp)
                    .padding(15.dp)
            ) {
                SpiraGPSTitle()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        localUriHandler.openUri("https://github.com/Bursur/SpiraGPS")
                    }
                ) {
                    Image(
                        painter = if(SpiraGPSDarkMode)
                            rememberImagePainter("https://bursur.github.io/SpiraGPS/github-white.png")
                        else
                            rememberImagePainter("https://bursur.github.io/SpiraGPS/github-black.png"),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "/SpiraGPS", style = SpiraGPSText.typography.info, color = textColour)
                }

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