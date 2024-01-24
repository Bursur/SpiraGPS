package spiragps.views.editor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun EntryEditorButton(modifier: Modifier = Modifier, entry: Entry, onDismiss: (Entry?) -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.fabBackgroundColour,
        contentColor = SpiraGPSColours.fabIconColour,
        modifier = modifier
    ) {
        Text(text = "Entry Editor", fontFamily = SpiraGPSText.fontFamily)
    }

    if(openAlertDialog)
        EntryEditor(sourceEntry = entry) {
            openAlertDialog = false
            onDismiss(it)
        }
}

@Composable
fun EntryEditor(sourceEntry: Entry, onDismiss: (Entry?) -> Unit) {
    var entry by remember { mutableStateOf(sourceEntry) }

    Dialog(
        onDismissRequest = { onDismiss(entry) },
    ) {
        var selectedEntryType by remember { mutableStateOf("info") }
        var entryTypeExpanded by remember { mutableStateOf(false) }

        val typeSelectedCallback: (String) -> Unit = { entryType: String ->
            selectedEntryType = entryType
            entryTypeExpanded = false
            entry = Entry(type = selectedEntryType)
        }

        Surface(elevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp)
            ) {
                Text(text= "Edit Entry", fontFamily = SpiraGPSText.fontFamily, fontSize = 35.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 15.dp))

                // Selector Type
                Row {
                    Text(
                        text = "Type (Click To Change): $selectedEntryType",
                        fontFamily = SpiraGPSText.fontFamily,
                        fontSize = 20.sp,
                        modifier = Modifier.clickable { entryTypeExpanded = true }.padding(bottom = 10.dp)
                    )

                    DropdownMenu(
                        expanded = entryTypeExpanded,
                        onDismissRequest = {
                            entryTypeExpanded = false
                        }
                    ) {
                        EntryType("info", onClick = typeSelectedCallback)
                        EntryType("image", onClick = typeSelectedCallback)
                        EntryType("bullets", onClick = typeSelectedCallback)
                        EntryType("table", onClick = typeSelectedCallback)
                    }
                }

                // Entry Editor Panel
                createEditorPanel(Entry(type = selectedEntryType)) {
                    entry = it
                }

                // Save Button
                Row {
                    TextButton(onClick = { onDismiss(entry) }) {
                        Text(text = "Save", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = Color.Black))
                    }

                    // Cancel Button
                    TextButton(onClick = { onDismiss(null) }) {
                        Text(text = "Cancel", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = Color.Black))
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
                fontFamily = SpiraGPSText.fontFamily
            )
        },
        onClick = {
            onClick(value)
        }
    )
}