package com.bursur.spiragps.route.entries

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bursur.spiragps.editor.createEditorPanel
import com.bursur.spiragps.route.conditions.ConditionSelector
import com.bursur.spiragps.route.conditions.getConditionString
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun EntryEditorButton(modifier: Modifier = Modifier, entry: Entry, conditions: ArrayList<Condition>, isEditButton: Boolean = false, onDismiss: (Entry?) -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = {
            openAlertDialog = true
        },
        modifier = modifier
    ) {
        Text(text = if(isEditButton) "Edit Entry" else "Add Entry", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
    }

    if(openAlertDialog)
        EntryEditor(entry, conditions) {
            openAlertDialog = false
            onDismiss(it)
        }
}

@Composable
fun EntryEditor(entry: Entry, conditions: ArrayList<Condition>, onDismiss: (Entry?) -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss(null) },
    ) {
        var selectedEntryType by remember { mutableStateOf("info") }
        var entryTypeExpanded by remember { mutableStateOf(false) }

        var selectedCondition by remember { mutableStateOf(getConditionString(entry)) }
        var conditionExpanded by remember { mutableStateOf(false) }

        val typeSelectedCallback: (String) -> Unit = { entryType: String ->
            selectedEntryType = entryType
            entryTypeExpanded = false
            entry.type = selectedEntryType
        }

        Surface(shadowElevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp).animateContentSize()
            ) {
                Text(text= "Edit Entry", style = SpiraGPSText.typography.routeTitle, color = SpiraGPSColours.text, modifier = Modifier.padding(bottom = 15.dp))

                // Selector Type
                Row {
                    Text(
                        text = "Type: $selectedEntryType",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text,
                        modifier = Modifier.clickable { entryTypeExpanded = true }.padding(bottom = 10.dp)
                    )

                    DropdownMenu(
                        expanded = entryTypeExpanded,
                        onDismissRequest = { entryTypeExpanded = false },
                        modifier = Modifier.background(SpiraGPSColours.infoBackground)
                    ) {
                        EntryType("info", onClick = typeSelectedCallback)
                        EntryType("image", onClick = typeSelectedCallback)
                        EntryType("bullets", onClick = typeSelectedCallback)
                        EntryType("table", onClick = typeSelectedCallback)
                    }
                }

                // Entry Editor Panel
                Crossfade(targetState = selectedEntryType) {
                    createEditorPanel(entry, conditions)
                }

                // Condition List
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Condition: $selectedCondition",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text,
                        modifier = Modifier.clickable { conditionExpanded = true }.padding(bottom = 10.dp)
                    )

                    if(conditionExpanded)
                        ConditionSelector(entry, conditions) {
                            selectedCondition = getConditionString(entry)
                            conditionExpanded = false
                        }
                }

                // Save Button
                Row {
                    TextButton(onClick = { onDismiss(entry) }) {
                        Text(text = "Save", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                    }

                    // Cancel Button
                    TextButton(onClick = { onDismiss(null) }) {
                        Text(text = "Cancel", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                    }
                }
            }
        }
    }
}

@Composable
private fun EntryType(value: String, onClick: (String) -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = value,
                style = SpiraGPSText.typography.info,
                color = SpiraGPSColours.text
            )
        },
        onClick = {
            onClick(value)
        }
    )
}