package com.bursur.spiragps.route.entries.customise

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletPointEditor
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.BasePanelEditor
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun CustomisationEditorPanel(entry: Entry, selectedEntry: Entry) {
    var item by remember { mutableStateOf(entry.item) }
    val steps by remember { mutableStateOf(entry.guide) }
    var newStep by remember { mutableStateOf("") }
    var updates by remember { mutableStateOf(0) }

    if(selectedEntry == entry) {
        BasePanelEditor(border = SpiraGPSColours.customiseBorder) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Item:",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
                TextEdit(text = item, placeholderText = "Item Name...", multiLine = false) {
                    item = it
                    entry.item = item
                }
            }

            Text(
                text = "Customisations:",
                style = SpiraGPSText.typography.info,
                color = SpiraGPSColours.text,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            key(updates) {
                steps.forEachIndexed { index, step ->
                    BulletPointEditor(
                        text = step,
                        placeholderText = "Update Customisation...",
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
                    placeholderText = "Enter New Customisation...",
                    multiLine = false,
                    onEnterKey = {
                        steps.add(newStep)
                        newStep = ""
                        entry.guide = steps
                    },
                    modifier = Modifier.weight(1f)
                ) { newStep = it }

                TextButton(
                    onClick = {
                        steps.add(newStep)
                        newStep = ""
                        entry.guide = steps
                    }
                ) {
                    Text(
                        text = "Add",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text
                    )
                }
            }
        }
    }
    else
        CustomiseView(entry)
}