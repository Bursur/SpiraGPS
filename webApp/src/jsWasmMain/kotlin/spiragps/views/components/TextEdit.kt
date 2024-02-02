package spiragps.views.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun TextEdit(modifier: Modifier = Modifier, text: String, placeholderText: String, isBold: Boolean = false, onValueChange: (String) -> Unit) {
    val textColour = animateColorAsState(SpiraGPSColours.value.text)
    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
    val indicatorColour = animateColorAsState(SpiraGPSColours.value.toggleSelectedTrackColour)
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = textColour.value,
            cursorColor = textColour.value,
            backgroundColor = bgColour.value,
            focusedIndicatorColor = indicatorColour.value
        ),
        textStyle = if(isBold) SpiraGPSText.typography.value.infoBold else SpiraGPSText.typography.value.info,
        placeholder = {
            Text(
                text = placeholderText,
                style = SpiraGPSText.typography.value.info,
                color = textColour.value,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}