package com.bursur.spiragps.components.link

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun LinkEditorPanel(entry: Entry, selectedEntry: Entry) {
    var linkText by remember { mutableStateOf(entry.text) }
    var linkUrl by remember { mutableStateOf(entry.link) }
    val textColour by animateColorAsState(SpiraGPSColours.text)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.animateContentSize()
    ) {
        if(selectedEntry == entry) {
            // Text
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Link Text:",
                    style = SpiraGPSText.typography.infoBold,
                    color = textColour
                )

                TextEdit(
                    text = linkText,
                    placeholderText = "Set link text...",
                    multiLine = false,
                    onEnterKey = {
                        entry.text = linkText
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    linkText = it
                    entry.text = linkText
                }
            }

            // Link
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Link URL:",
                    style = SpiraGPSText.typography.infoBold,
                    color = textColour
                )

                TextEdit(
                    text = linkUrl,
                    placeholderText = "Set link URL...",
                    multiLine = false,
                    onEnterKey = {
                        entry.link = linkUrl
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    linkUrl = it
                    entry.link = linkUrl
                }
            }
        }
        else {
            val uriHandler = LocalUriHandler.current
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Link,
                    contentDescription = null,
                    tint = textColour,
                    modifier = Modifier.clickable {
                        uriHandler.openUri(entry.link)
                    }
                )
                Link(entry, true)
            }
        }
    }
}