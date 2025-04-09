package com.bursur.spiragps.components.link

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun Link(entry: Entry, editor: Boolean = false) {
    val textColour by animateColorAsState(SpiraGPSColours.link)
    val uriHandler = LocalUriHandler.current

    Text(
        text = entry.text,
        style = SpiraGPSText.typography.info,
        color = textColour,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .then(
                if(!editor)
                    Modifier.clickable {
                        uriHandler.openUri(entry.link)
                    }
                else
                    Modifier
            )
    )
}