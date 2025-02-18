package com.bursur.spiragps.route.entries.battle

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun BattleEditorPanel(entry: Entry) {
    var enemy by remember { mutableStateOf(entry.enemy) }
    var health by remember { mutableStateOf(entry.health) }
    val steps by remember { mutableStateOf(entry.guide) }
    var newStep by remember { mutableStateOf("") }

    var updates by remember { mutableStateOf(0) }

    BasePanelEditor(border = SpiraGPSColours.battleBorder) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "Enemy:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            TextEdit(text = enemy, placeholderText = "Enemy Name...") {
                enemy = it
                entry.enemy = enemy
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "Health:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            TextEdit(
                text = if (health != 0) health.toString() else "",
                placeholderText = "Enemy Health..."
            ) {
                health = try {
                    it.toInt(10)
                } catch (e: Exception) {
                    health
                }

                entry.health = health
            }
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Steps:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text, modifier = Modifier.padding(horizontal = 5.dp))
        key(updates) {
            steps.forEachIndexed { index, step ->
                BulletPointEditor(
                    text = step,
                    placeholderText = "Update Step...",
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
                placeholderText = "Enter New Step...",
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

@Composable
fun BattleEditorPanel(entry: Entry, selectedEntry: Entry) {
    var enemy by remember { mutableStateOf(entry.enemy) }
    var health by remember { mutableStateOf(entry.health) }
    val steps by remember { mutableStateOf(entry.guide) }
    var newStep by remember { mutableStateOf("") }

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
                TextEdit(text = enemy, placeholderText = "Enemy Name...") {
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
                    placeholderText = "Enemy Health..."
                ) {
                    health = try {
                        it.toInt(10)
                    } catch (e: Exception) {
                        health
                    }

                    entry.health = health
                }
            }

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Steps:",
                style = SpiraGPSText.typography.info,
                color = SpiraGPSColours.text,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            key(updates) {
                steps.forEachIndexed { index, step ->
                    BulletPointEditor(
                        text = step,
                        placeholderText = "Update Step...",
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
                    placeholderText = "Enter New Step...",
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
        BattleView(entry)
}