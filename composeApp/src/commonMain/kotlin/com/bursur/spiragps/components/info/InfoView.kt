package com.bursur.spiragps.components.info

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.highlightKeywords
import com.mohamedrejeb.richeditor.model.rememberRichTextState
@Composable
fun InfoView(entry: Entry) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    val state = rememberRichTextState()

    LaunchedEffect(Unit) {
        state.setHtml(entry.text)
    }

    Text(
        text = highlightKeywords(state.annotatedString),
        style = if(entry.bold) SpiraGPSText.typography.bulletTitleBold else SpiraGPSText.typography.bulletTitle,
        color = textColour,
        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
    )
}