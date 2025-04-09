package com.bursur.spiragps.route.title

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun TitleEditor(title: String, onValueChange: (String) -> Unit) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)
    val indicatorColour by animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)

    TextField(
        value = title,
        maxLines = 1,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = textColour,
            focusedTextColor = textColour,
            cursorColor = textColour,
            unfocusedContainerColor = bgColour,
            focusedContainerColor = bgColour,
            focusedIndicatorColor = indicatorColour
        ),
        textStyle = SpiraGPSText.typography.routeTitle,
        placeholder = {
            Text(
                text = "Enter Title...",
                style = SpiraGPSText.typography.chapterTitle,
                color = SpiraGPSColours.text,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(10.dp))
}