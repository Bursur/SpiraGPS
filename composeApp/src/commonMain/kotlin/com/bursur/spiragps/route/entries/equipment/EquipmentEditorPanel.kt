package com.bursur.spiragps.route.entries.equipment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletPointEditor
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.BasePanelEditor
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun EquipmentEditorPanel(entry: Entry) {
    val steps by remember { mutableStateOf(entry.guide) }
    var newStep by remember { mutableStateOf("") }
    var updates by remember { mutableStateOf(0) }

    BasePanelEditor(border = SpiraGPSColours.equipmentBorder) {
        Text(text = "Equipment:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text, modifier = Modifier.padding(horizontal = 5.dp))
        key(updates) {
            steps.forEachIndexed { index, step ->
                BulletPointEditor(
                    text = step,
                    placeholderText = "Update Equipment...",
                    onUpdated = {
                        steps[index] = it
                        entry.guide = steps
                    },
                    onDeleted = {
                        steps.removeAt(index)
                        entry.guide = steps
                        ++updates
                    }
                )
            }
        }

        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            TextEdit(
                text = newStep,
                placeholderText = "Add Equipment...",
                modifier = Modifier.weight(1f)
            ) { newStep = it }

            TextButton(
                onClick = {
                    steps.add(newStep)
                    newStep = ""
                    entry.guide = steps
                }
            ) {
                Text(text = "Add", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            }
        }
    }
}