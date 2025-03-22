package com.bursur.spiragps.components.bulletedlist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
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
fun BulletEditorPanel(entry: Entry, selectedEntry: Entry) {
    val entries: ArrayList<String> by remember { mutableStateOf(entry.guide) }
    var updates by remember { mutableStateOf(0) }
    var newPoint by remember { mutableStateOf("") }
    var isBold by remember { mutableStateOf(entry.bold) }
    var title by remember { mutableStateOf(entry.text) }

    Column(modifier = Modifier.animateContentSize()) {
        if (entry == selectedEntry) {
            val focusRequester = remember { FocusRequester() }
            key(updates) {
                // Weight
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Bold:",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text
                    )
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

                // Title
                TextEdit(text = title, placeholderText = "Enter Title...", isBold = isBold) {
                    title = it
                    entry.text = title
                }

                // Entries
                entries.forEachIndexed { index, step ->
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

                Row {
                    TextEdit(
                        text = newPoint,
                        placeholderText = "Enter New Point...",
                        modifier = Modifier.weight(1f).focusRequester(focusRequester),
                        multiLine = false,
                        hasPasteButton = true,
                        onEnterKey = {
                            entries.add(newPoint)
                            newPoint = ""
                            entry.guide = entries
                            ++updates
                        }
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
                            style = SpiraGPSText.typography.info,
                            color = SpiraGPSColours.text
                        )
                    }
                }
            }

            LaunchedEffect(updates) {
                focusRequester.requestFocus()
            }
        } else
            BulletedList(entry)
    }
}