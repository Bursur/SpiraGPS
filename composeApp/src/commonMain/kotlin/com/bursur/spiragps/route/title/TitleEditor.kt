package com.bursur.spiragps.route.title

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun TitleEditor(title: String, onValueChange: (String) -> Unit) {
    val textColour = animateColorAsState(SpiraGPSColours.text)
    val bgColour = animateColorAsState(SpiraGPSColours.infoBackground)
    val indicatorColour = animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)

    TextField(
        value = title,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = textColour.value,
            focusedTextColor = textColour.value,
            cursorColor = textColour.value,
            unfocusedContainerColor = bgColour.value,
            focusedContainerColor = bgColour.value,
            focusedIndicatorColor = indicatorColour.value
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