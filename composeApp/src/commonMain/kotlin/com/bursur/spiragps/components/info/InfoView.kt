package com.bursur.spiragps.components.info

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.highlightKeywords

@Composable
fun InfoView(entry: Entry) {
    val textColour = animateColorAsState(SpiraGPSColours.text)
    Text(
        text = highlightKeywords(entry.text),
        style = if(entry.bold) SpiraGPSText.typography.bulletTitleBold else SpiraGPSText.typography.bulletTitle,
        color = textColour.value,
        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
    )
}