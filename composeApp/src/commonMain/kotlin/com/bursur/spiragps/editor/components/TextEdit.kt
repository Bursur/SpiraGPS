package com.bursur.spiragps.editor.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.utils.ClipboardService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEdit(modifier: Modifier = Modifier, text: String, placeholderText: String, isBold: Boolean = false, multiLine: Boolean = true, hasPasteButton: Boolean = false, onEnterKey: () -> Unit = {}, onValueChange: (String) -> Unit) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)
    val indicatorColour by animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)

    var awaitingClipboardData by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {

        BasicTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            textStyle = (if (isBold)
                            SpiraGPSText.typography.infoBold
                        else
                            SpiraGPSText.typography.info)
                .copy(color = textColour),
            cursorBrush = SolidColor(textColour),
            maxLines = if(multiLine) Int.MAX_VALUE else 1,
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .onKeyEvent {
                    if (it.key.keyCode == Key.Enter.keyCode)
                        onEnterKey()
                    true
                }
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                contentPadding = PaddingValues(0.dp),
                singleLine = false,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                enabled = true,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = placeholderText,
                        style = SpiraGPSText.typography.info,
                        color = textColour,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = textColour,
                    focusedTextColor = textColour,
                    cursorColor = textColour,
                    unfocusedContainerColor = bgColour,
                    focusedContainerColor = bgColour,
                    focusedIndicatorColor = indicatorColour
                )
            )
        }

        if(hasPasteButton) {
            Button(
                onClick = { awaitingClipboardData = true },
                colors = ButtonDefaults.buttonColors(containerColor = bgColour),
                contentPadding = PaddingValues(3.dp),
                modifier = Modifier.defaultMinSize(1.dp, 1.dp)
            ) {
                AsyncImage(
                    model = "https://bursur.github.io/SpiraGPS/paste.png",
                    contentDescription = null,
                    modifier = Modifier.padding(2.dp).width(15.dp).height(15.dp)
                )
            }
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