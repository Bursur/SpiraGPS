package com.bursur.spiragps.route.conditions

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import com.bursur.spiragps.components.bulletedlist.BulletPointEditor
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ConditionPointEditor(condition: Condition, onDismiss: () -> Unit) {
    val options: ArrayList<String> by remember { mutableStateOf(condition.options) }
    var newItem by remember { mutableStateOf("") }
    var updates by remember { mutableStateOf(0) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(
            shadowElevation = 5.dp,
            shape = RoundedCornerShape(20.dp),
            color = SpiraGPSColours.background
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Set the Options for this Condition",
                    style = SpiraGPSText.typography.chapterTitle,
                    color = SpiraGPSColours.text
                )
                key(updates) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp)
                            .animateContentSize()
                    ) {
                        // Entries
                        options.forEachIndexed { index, step ->
                            BulletPointEditor(
                                text = step,
                                placeholderText = "Update Option...",
                                onUpdated = {
                                    options[index] = it
                                    condition.options = options
                                },
                                onDeleted = {
                                    options.removeAt(index)
                                    condition.options = options
                                    ++updates
                                }
                            )
                        }

                        Row {
                            TextEdit(
                                text = newItem,
                                placeholderText = "Enter New Option...",
                                modifier = Modifier.weight(1f)
                            ) { newItem = it }

                            TextButton(
                                onClick = {
                                    options.add(newItem)
                                    newItem = ""
                                    condition.options = options
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

                        TextButton(onClick = onDismiss) {
                            Text(
                                text = "Done",
                                style = SpiraGPSText.typography.info,
                                color = SpiraGPSColours.text
                            )
                        }
                    }
                }
            }
        }
    }
}