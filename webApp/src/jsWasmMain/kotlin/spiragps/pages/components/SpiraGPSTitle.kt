package spiragps.pages.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.style.SpiraGPSVersion

@Composable
fun SpiraGPSTitle() {
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Column {
        Text(text = "Spira GPS", style = SpiraGPSText.typography.value.landingTitle, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text(text = "(v$SpiraGPSVersion)", style = SpiraGPSText.typography.value.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}