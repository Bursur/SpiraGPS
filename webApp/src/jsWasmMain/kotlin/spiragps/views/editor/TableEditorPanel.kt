package spiragps.views.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.TableView
import spiragps.views.components.TextEdit

@Composable
fun TableEditorPanel(entry: Entry) {
    val entries: ArrayList<String> by remember { mutableStateOf(entry.guide) }
    var newItem by remember { mutableStateOf("") }
    var updates by remember { mutableStateOf(0) }

    entry.columns = 2

    key(updates) {
        LazyColumn {
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
                        text = newItem,
                        placeholderText = "Enter New Point...",
                        modifier = Modifier.weight(1f)
                    ) { newItem = it }

                    TextButton(
                        onClick = {
                            entries.add(newItem)
                            newItem = ""
                            entry.guide = entries
                            ++updates
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
    }
}