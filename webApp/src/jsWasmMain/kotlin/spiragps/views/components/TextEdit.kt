package spiragps.views.components

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
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = SpiraGPSColours.text,
            backgroundColor = SpiraGPSColours.infoBackground,
            focusedIndicatorColor = SpiraGPSColours.toggleSelectedTrackColour
        ),
        textStyle = if(isBold) SpiraGPSText.typography.infoBold else SpiraGPSText.typography.info,
        placeholder = {
            Text(
                text = placeholderText,
                fontFamily = SpiraGPSText.fontFamily,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}