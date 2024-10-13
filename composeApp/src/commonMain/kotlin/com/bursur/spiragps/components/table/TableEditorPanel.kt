package com.bursur.spiragps.components.table

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bursur.spiragps.components.bulletedlist.BulletPointEditor
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

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