package com.bursur.spiragps.components.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun InfoEditorPanel(entry: Entry) {
    var infoText by remember { mutableStateOf(entry.text) }
    var isBold by remember { mutableStateOf(entry.bold) }

    Column {
        // Weight
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 5.dp)
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

        // Text
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Text:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            TextEdit(text = infoText, placeholderText = "Enter Text...", isBold = isBold) {
                infoText = it
                entry.text = infoText
            }
        }
    }
}