package com.bursur.spiragps.route.entries.battle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.editor.isPanel
import com.bursur.spiragps.editor.secondaryEntry
import com.bursur.spiragps.route.BasePanelEditor
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun BattleEditorPanel(entry: Entry, selectedEntry: Entry, conditions: ArrayList<Condition>) {
    var enemy by remember { mutableStateOf(entry.enemy) }
    var health by remember { mutableStateOf(entry.health) }
    var actions by remember { mutableStateOf(entry.actions) }
    val entries by remember { mutableStateOf(entry.entries) }
    var updates by remember { mutableStateOf(0) }

    if(selectedEntry == entry) {
        BasePanelEditor(border = SpiraGPSColours.battleBorder) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Enemy:",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
                TextEdit(text = enemy, placeholderText = "Enemy Name...", multiLine = false) {
                    enemy = it
                    entry.enemy = enemy
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Health:",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
                TextEdit(
                    text = if (health != 0) health.toString() else "",
                    placeholderText = "Enemy Health...",
                    multiLine = false
                ) {
                    health = try {
                        it.toInt(10)
                    } catch (e: Exception) {
                        health
                    }

                    entry.health = health
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Actions:",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
                TextEdit(
                    text = if (actions != 0) actions.toString() else "",
                    multiLine = false,
                    placeholderText = "Action Count..."
                ) {
                    actions = try {
                        it.toInt(10)
                    } catch (e: Exception) {
                        actions
                    }

                    entry.actions = actions
                }
            }

            Spacer(modifier = Modifier.size(10.dp))
            key(updates) {
                Column(modifier = Modifier.padding(10.dp)) {
                    entries.forEach {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
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
        BattleView(entry)
}