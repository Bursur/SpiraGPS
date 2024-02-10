package spiragps.views.editor.panels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Condition
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.components.EditContextMenu
import spiragps.views.createEntry
import spiragps.views.editor.EntryEditorButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpheregridEditorPanel(entry: Entry, conditions: ArrayList<Condition>) {
    val entries by remember { mutableStateOf(entry.entries) }
    var updates by remember { mutableStateOf(0) }

    var editControlOpen by remember { mutableStateOf(false) }
    var selectedEntry by remember { mutableStateOf(Entry()) }

    BasePanelEditor(border = SpiraGPSColours.value.blitzballBorder) {
        key(updates) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(items = entries) {
                    Column(
                        modifier = Modifier
                            .combinedClickable(
                                onLongClick = {
                                    editControlOpen = true
                                    selectedEntry = it
                                },
                                onClick = {}
                            )
                    ) {
                        createEntry(it)
                    }

                    if(editControlOpen && selectedEntry == it) {
                        EditContextMenu(
                            open = editControlOpen,
                            entry = it,
                            conditions = conditions,
                            onDismiss = { editControlOpen = false },
                            onEntryUpdated = {
                                editControlOpen = false
                                ++updates
                            },
                            onEntryDeleted = {
                                editControlOpen = false
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
                                    editControlOpen = false
                                }
                            },
                            onMoveDown = {
                                entry.entries.apply {
                                    val index = indexOf(it)
                                    if(index != -1 && index < size - 1) {
                                        add(index + 1, removeAt(index))
                                        ++updates
                                    }
                                    editControlOpen = false
                                }
                            }
                        )
                    }
                }

                item {
                    EntryEditorButton(entry = Entry(type = "info"), conditions = conditions) {
                        if (it != null) {
                            entry.entries.add(it)
                            ++updates
                        }
                    }
                }
            }
        }
    }
}