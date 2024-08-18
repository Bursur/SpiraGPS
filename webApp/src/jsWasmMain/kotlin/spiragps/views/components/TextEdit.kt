package spiragps.views.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.ClipboardService
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.editor.EntryEditorButton
import spiragps.views.editor.panels.PanelEditorButton

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TextEdit(modifier: Modifier = Modifier, text: String, placeholderText: String, isBold: Boolean = false, onValueChange: (String) -> Unit) {
    val textColour = animateColorAsState(SpiraGPSColours.text)
    val bgColour = animateColorAsState(SpiraGPSColours.infoBackground)
    val indicatorColour = animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)

    var awaitingClipboardData by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
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
            textStyle = if (isBold) SpiraGPSText.typography.infoBold else SpiraGPSText.typography.info,
            placeholder = {
                Text(
                    text = placeholderText,
                    style = SpiraGPSText.typography.info,
                    color = textColour.value,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        )

        Button(
            onClick = { awaitingClipboardData = true },
            colors = ButtonDefaults.buttonColors(backgroundColor = bgColour.value)
        ) {
            Image(painter = painterResource("SpiraGPS/paste.png"), contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
        }
    }

    LaunchedEffect(awaitingClipboardData) {
        if(awaitingClipboardData) {
            val data = ClipboardService.getData()
            awaitingClipboardData = false
            onValueChange(data)
        }
    }
}