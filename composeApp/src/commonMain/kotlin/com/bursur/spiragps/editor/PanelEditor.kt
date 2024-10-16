package com.bursur.spiragps.editor

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import com.bursur.spiragps.route.conditions.ConditionSelector
import com.bursur.spiragps.route.conditions.getConditionString
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun PanelEditorButton(modifier: Modifier = Modifier, entry: Entry, conditions: ArrayList<Condition>, isEditButton: Boolean = false, onDismiss: (Entry?) -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = {
            openAlertDialog = true
        },
        modifier = modifier
    ) {
        Text(text = if(isEditButton) "Edit Panel" else "Add Panel", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
    }

    if(openAlertDialog)
        PanelEditor(entry, conditions) {
            openAlertDialog = false
            onDismiss(it)
        }
}

@Composable
fun PanelEditor(entry: Entry, conditions: ArrayList<Condition>, onDismiss: (Entry?) -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss(null) },
    ) {
        var selectedEntryType by remember { mutableStateOf(entry.type) }
        var entryTypeExpanded by remember { mutableStateOf(false) }

        var minimised by remember { mutableStateOf(entry.minimised) }

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
                Text(text= "Edit Panel", style = SpiraGPSText.typography.routeTitle, color = SpiraGPSColours.text, modifier = Modifier.padding(bottom = 15.dp))

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
                        EntryType("battle", onClick = typeSelectedCallback)
                        EntryType("encounter", onClick = typeSelectedCallback)
                        EntryType("trial", onClick = typeSelectedCallback)
                        EntryType("shop", onClick = typeSelectedCallback)
                        EntryType("equipment", onClick = typeSelectedCallback)
                        EntryType("itemsort", onClick = typeSelectedCallback)
                        EntryType("spheregrid", onClick = typeSelectedCallback)
                        EntryType("customise", onClick = typeSelectedCallback)
                        EntryType("blitzball", onClick = typeSelectedCallback)
                        EntryType("tip", onClick = typeSelectedCallback)
                    }
                }

                // Entry Editor Panel
                Crossfade(targetState = selectedEntryType) {
                    createEditorPanel(entry, conditions)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Start Minimised:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                    Checkbox(
                        checked = minimised,
                        onCheckedChange = {
                            minimised = it
                            entry.minimised = minimised
                        },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = SpiraGPSColours.toggleUnselectedTrackColour,
                            checkedColor = SpiraGPSColours.toggleSelectedTrackColour
                        )
                    )
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