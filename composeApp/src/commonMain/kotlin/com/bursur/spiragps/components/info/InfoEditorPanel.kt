package com.bursur.spiragps.components.info

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun InfoEditorPanel(entry: Entry, selectedEntry: Entry) {
    var infoText by remember { mutableStateOf(entry.text) }
    var isBold by remember { mutableStateOf(entry.bold) }

    Column(modifier = Modifier.animateContentSize()) {
        if(selectedEntry == entry) {
            // Weight
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Bold:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                Checkbox(
                    checked = isBold,
                    onCheckedChange = {
                        isBold = it
                        entry.bold = isBold
                    },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = SpiraGPSColours.toggleUnselectedTrackColour,
                        checkedColor = SpiraGPSColours.toggleSelectedTrackColour
                    )
                )
            }

            TextEdit(text = infoText, placeholderText = "Enter Text...", isBold = isBold) {
                infoText = it
                entry.text = infoText
            }
        }
        else
            InfoView(entry)
    }
}