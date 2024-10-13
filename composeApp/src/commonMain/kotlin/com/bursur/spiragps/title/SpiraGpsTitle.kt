package com.bursur.spiragps.title

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.version.SpiraGPSVersion

@Composable
fun SpiraGPSTitle() {
    val textColour = animateColorAsState(SpiraGPSColours.text)

    Column {
        Text(text = "Spira GPS", style = SpiraGPSText.typography.landingTitle, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text(text = "(v$SpiraGPSVersion)", style = SpiraGPSText.typography.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}