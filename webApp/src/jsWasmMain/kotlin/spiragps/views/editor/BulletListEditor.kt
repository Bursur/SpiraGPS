package spiragps.views.editor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@Composable
fun BulletEditorPanel(entry: Entry) {
    val entries: ArrayList<String> by remember { mutableStateOf(entry.guide) }
    var updates by remember { mutableStateOf(0) }
    var newPoint by remember { mutableStateOf("") }
    var isBold by remember { mutableStateOf(entry.bold) }
    var title by remember { mutableStateOf(entry.text) }

    key(updates) {
        LazyColumn {
            item {
                // Weight
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Text(
                        text = "Bold:",
                        style = SpiraGPSText.typography.value.info,
                        color = SpiraGPSColours.value.text
                    )
                    Checkbox(
                        checked = isBold,
                        onCheckedChange = {
                            isBold = it
                            entry.bold = isBold
                        },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = SpiraGPSColours.value.toggleUnselectedTrackColour,
                            checkedColor = SpiraGPSColours.value.toggleSelectedTrackColour
                        )
                    )
                }
            }

            // Title
            item {
                TextEdit(text = title, placeholderText = "Enter Title...", isBold = isBold) {
                    title = it
                    entry.text = title
                }
            }

            // Entries
            itemsIndexed(items = entries) { index, step ->
                BulletPointEditor(
                    text = step,
                    placeholderText = "Update Point...",
                    onUpdated = {
                        entries[index] = it
                        entry.guide = entries
                    },
                    onDeleted = {
                        entries.removeAt(index)
                        entry.guide = entries
                        ++updates
                    }
                )
            }

            item {
                Row {
                    TextEdit(
                        text = newPoint,
                        placeholderText = "Enter New Point...",
                        modifier = Modifier.weight(1f)
                    ) { newPoint = it }

                    TextButton(
                        onClick = {
                            entries.add(newPoint)
                            newPoint = ""
                            entry.guide = entries
                            ++updates
                        }
                    ) {
                        Text(
                            text = "Add",
                            style = SpiraGPSText.typography.value.info,
                            color = SpiraGPSColours.value.text
                        )
                    }
                }
            }
        }
    }
}