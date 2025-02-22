package com.bursur.spiragps.route.entries.tip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.ControlPanel
import com.bursur.spiragps.editor.isPanel
import com.bursur.spiragps.editor.secondaryEntry
import com.bursur.spiragps.route.BasePanelEditor
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.theme.SpiraGPSColours

@Composable
fun TipEditorPanel(entry: Entry, selectedEntry: Entry, conditions: ArrayList<Condition>) {
    val entries by remember { mutableStateOf(entry.entries) }
    var updates by remember { mutableStateOf(0) }

    var editControlOpen by remember { mutableStateOf(false) }

    if(entry == selectedEntry) {
        BasePanelEditor(border = SpiraGPSColours.tipBorder) {
            key(updates) {
                Column(modifier = Modifier.padding(10.dp)) {
                    entries.forEach {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    editControlOpen = true
                                    secondaryEntry = it
                                }
                        ) {
                            if(it == secondaryEntry)
                                ControlPanel(
                                    entry = it,
                                    conditions = conditions,
                                    onEntryDeleted = {
                                        if(entry.entries.remove(it))
                                            ++updates
                                    },
                                    onMoveUp = {
                                        entry.entries.apply {
                                            val index = indexOf(it)
                                            if(index >= 1) {
                                                add(index - 1, removeAt(index))
                                                ++updates
                                            }
                                        }
                                    },
                                    onMoveDown = {
                                        entry.entries.apply {
                                            val index = indexOf(it)
                                            if(index != -1 && index < size - 1) {
                                                add(index + 1, removeAt(index))
                                                ++updates
                                            }
                                        }
                                    }
                                )

                            createEntry(entry = it, editor = true, selectedEntry = secondaryEntry, conditions = conditions)
                        }
                    }

                    EntryEditorButton(entry = Entry(type = "info"), isPanel = isPanel(entry)) {
                        if (it != null) {
                            entry.entries.add(it)
                            ++updates
                            secondaryEntry = it
                        }
                    }
                }
            }
        }
    }
    else
        TipView(entry)
}