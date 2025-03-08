package com.bursur.spiragps.components.info

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FormatBold
import androidx.compose.material.icons.outlined.FormatItalic
import androidx.compose.material.icons.outlined.FormatStrikethrough
import androidx.compose.material.icons.outlined.FormatUnderlined
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoEditorPanel(entry: Entry, selectedEntry: Entry) {
    val state = rememberRichTextState()
    val infoText by remember(state.annotatedString) { mutableStateOf(state.toHtml()) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.animateContentSize()) {
        if(selectedEntry == entry) {
            LaunchedEffect(Unit) {
                state.setHtml(entry.text)
            }

            InfoEditControlPanel(state)
            RichTextEditor(
                state = state,
                textStyle = TextStyle(
                    fontWeight = SpiraGPSText.typography.info.fontWeight,
                    fontFamily = SpiraGPSText.typography.info.fontFamily,
                    fontSize = SpiraGPSText.typography.info.fontSize
                ),
                colors = RichTextEditorDefaults.richTextEditorColors(
                    textColor = SpiraGPSColours.text,
                    cursorColor = SpiraGPSColours.text,
                    containerColor = SpiraGPSColours.infoBackground
                ),
                modifier = Modifier.fillMaxWidth()
            )

            LaunchedEffect(infoText) {
                entry.text = infoText
            }
        }
        else {
            InfoView(entry)
        }
    }
}

@Composable
private fun InfoEditControlPanel(state: RichTextState) {
    val bgColour by animateColorAsState(SpiraGPSColours.editorControlBackground)

    Row(modifier = Modifier.padding(3.dp).background(bgColour, RoundedCornerShape(10.dp))) {
        // Bold
        InfoEditControlPanelButton(
            Icons.Outlined.FormatBold,
            state.currentSpanStyle.fontWeight == SpiraGPSText.typography.infoBold.fontWeight
        ) {
            state.toggleSpanStyle(
                SpanStyle(
                    fontWeight = if(state.currentSpanStyle.fontWeight == SpiraGPSText.typography.infoBold.fontWeight)
                        SpiraGPSText.typography.info.fontWeight
                    else
                        SpiraGPSText.typography.infoBold.fontWeight,
                )
            )
        }

        // Italic
        InfoEditControlPanelButton(
            Icons.Outlined.FormatItalic,
            state.currentSpanStyle.fontStyle == FontStyle.Italic
        ) {
            state.toggleSpanStyle(
                SpanStyle(
                    fontStyle = if(state.currentSpanStyle.fontStyle == FontStyle.Italic)
                        FontStyle.Normal
                    else
                        FontStyle.Italic,
                )
            )
        }

        // Underlined
        /*InfoEditControlPanelButton(Icons.Outlined.FormatUnderlined) {
            state.toggleSpanStyle(
                SpanStyle(
                    textDecoration = if(state.currentSpanStyle.textDecoration != TextDecoration.Underline)
                        TextDecoration.Underline
                    else
                        TextDecoration.None,
                )
            )
        }*/

        // Strikethrough
        /*InfoEditControlPanelButton(Icons.Outlined.FormatStrikethrough) {
            state.toggleSpanStyle(
                SpanStyle(
                    textDecoration = if(state.currentSpanStyle.textDecoration == TextDecoration.LineThrough)
                        TextDecoration.None
                    else
                        TextDecoration.LineThrough,
                )
            )
        }*/
    }
}

@Composable
private fun InfoEditControlPanelButton(iconImage: ImageVector, selected: Boolean, onClick: () -> Unit) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    IconButton(
        onClick = onClick,
        modifier = Modifier.focusProperties { canFocus = false }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if(selected)
                        SpiraGPSColours.toggleSelectedTrackColour
                    else
                        SpiraGPSColours.toggleUnselectedTrackColour
                )
        ) {
            Icon(
                imageVector = iconImage,
                contentDescription = iconImage.name,
                tint = textColour,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}