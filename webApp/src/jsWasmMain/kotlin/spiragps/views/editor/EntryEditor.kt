package spiragps.views.editor

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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import spiragps.data.route.Condition
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun EntryEditorButton(modifier: Modifier = Modifier, entry: Entry, conditions: ArrayList<Condition>, isEditButton: Boolean = false, onDismiss: (Entry?) -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = {
            openAlertDialog = true
        },
        modifier = modifier
    ) {
        Text(text = if(isEditButton) "Edit Entry" else "Add Entry", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
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
        onDismissRequest = { onDismiss(null) },
    ) {
        var selectedEntryType by remember { mutableStateOf("info") }
        var entryTypeExpanded by remember { mutableStateOf(false) }

        var selectedCondition by remember { mutableStateOf(entry.requirement.condition.ifEmpty { "None" }) }
        var conditionExpanded by remember { mutableStateOf(false) }
        var conditionEnabled by remember { mutableStateOf(entry.requirement.state) }

        val typeSelectedCallback: (String) -> Unit = { entryType: String ->
            selectedEntryType = entryType
            entryTypeExpanded = false
            entry.type = selectedEntryType
        }

        val conditionSelectedCallback: (String) -> Unit = { condition: String ->
            selectedCondition = if(condition != "None") condition else ""
            conditionExpanded = false
            entry.requirement.condition = selectedCondition
        }

        Surface(elevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.value.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp).animateContentSize()
            ) {
                Text(text= "Edit Entry", style = SpiraGPSText.typography.value.routeTitle, color = SpiraGPSColours.value.text, modifier = Modifier.padding(bottom = 15.dp))

                // Selector Type
                Row {
                    Text(
                        text = "Type: $selectedEntryType",
                        style = SpiraGPSText.typography.value.info,
                        color = SpiraGPSColours.value.text,
                        modifier = Modifier.clickable { entryTypeExpanded = true }.padding(bottom = 10.dp)
                    )

                    DropdownMenu(
                        expanded = entryTypeExpanded,
                        onDismissRequest = { entryTypeExpanded = false },
                        modifier = Modifier.background(SpiraGPSColours.value.infoBackground)
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
                        style = SpiraGPSText.typography.value.info,
                        color = SpiraGPSColours.value.text,
                        modifier = Modifier.clickable { conditionExpanded = true }.padding(bottom = 10.dp)
                    )

                    Checkbox(
                        checked = conditionEnabled,
                        onCheckedChange = {
                            conditionEnabled = it
                            entry.requirement.state = it
                        },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = SpiraGPSColours.value.toggleUnselectedTrackColour,
                            checkedColor = SpiraGPSColours.value.toggleSelectedTrackColour
                        ),
                        modifier = Modifier.weight(.1f)
                    )

                    DropdownMenu(
                        expanded = conditionExpanded,
                        onDismissRequest = { conditionExpanded = false },
                        modifier = Modifier.background(SpiraGPSColours.value.infoBackground)
                    ) {
                        EntryType("None", onClick = conditionSelectedCallback)
                        conditions.forEach {
                            EntryType(it.name, onClick = conditionSelectedCallback)
                        }
                    }
                }

                // Save Button
                Row {
                    TextButton(onClick = { onDismiss(entry) }) {
                        Text(text = "Save", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
                    }

                    // Cancel Button
                    TextButton(onClick = { onDismiss(null) }) {
                        Text(text = "Cancel", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
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
                style = SpiraGPSText.typography.value.info,
                color = SpiraGPSColours.value.text
            )
        },
        onClick = {
            onClick(value)
        }
    )
}