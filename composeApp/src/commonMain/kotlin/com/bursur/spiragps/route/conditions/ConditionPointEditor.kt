package com.bursur.spiragps.route.conditions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ConditionPointEditor(text: String, enabled: Boolean, placeholderText: String = "", onUpdated: (String) -> Unit, onDeleted: () -> Unit, onToggle: (Boolean) -> Unit) {
    var newText by remember { mutableStateOf(text) }
    var isEnabled by remember { mutableStateOf(enabled) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 5.dp)) {
        TextEdit(
            text = newText,
            placeholderText = placeholderText,
            modifier = Modifier.weight(1f).padding(end = 5.dp)
        ) {
            newText = it
            onUpdated(newText)
        }

        Checkbox(
            checked = isEnabled,
            onCheckedChange = {
                isEnabled = it
                onToggle(isEnabled)
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = SpiraGPSColours.toggleUnselectedTrackColour,
                checkedColor = SpiraGPSColours.toggleSelectedTrackColour
            ),
            modifier = Modifier.weight(.1f)
        )

        TextButton(
            onClick = { onDeleted() },
            modifier = Modifier.weight(.1f).padding(end = 5.dp)
        ) {
            Text(text = "X", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
        }
    }
}