package spiragps.views.editor

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import spiragps.data.route.Chapter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.components.EditContextMenu
import spiragps.views.createEntry
import spiragps.views.editor.panels.PanelEditorButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterEditor(chapter: Chapter) {
    var title by remember { mutableStateOf(chapter.title) }
    val entries by remember { mutableStateOf(chapter.entries) }

    var updateCount by remember { mutableStateOf(0) }
    val bgColor = animateColorAsState(SpiraGPSColours.value.background)

    var editControlOpen by remember { mutableStateOf(false) }
    var selectedEntry by remember { mutableStateOf(Entry()) }

    Column {
        TitleEditor(title) {
            title = it
            chapter.title = title
        }

        key(updateCount) {
            entries.forEach {
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
                    createEntry(entry = it)
                    Divider(color = bgColor.value, thickness = 10.dp)

                    if(editControlOpen && selectedEntry == it) {
                        EditContextMenu(
                            open = editControlOpen,
                            entry = it,
                            onDismiss = { editControlOpen = false },
                            onEntryUpdated = {
                                editControlOpen = false
                                ++updateCount
                            },
                            onEntryDeleted = {
                                editControlOpen = false
                                if(chapter.entries.remove(it))
                                    ++updateCount
                            },
                            onMoveUp = {
                                chapter.entries.apply {
                                    val index = indexOf(it)
                                    if(index >= 1) {
                                        add(index - 1, removeAt(index))
                                        ++updateCount
                                    }
                                    editControlOpen = false
                                }
                            },
                            onMoveDown = {
                                chapter.entries.apply {
                                    val index = indexOf(it)
                                    if(index != -1 && index < size - 1) {
                                        add(index + 1, removeAt(index))
                                        ++updateCount
                                    }
                                    editControlOpen = false
                                }
                            }
                        )
                    }
                }
            }
        }

        Row {
            EntryEditorButton(entry = Entry(type = "info")) {
                if (it != null) {
                    entries.add(it)
                    ++updateCount
                }
            }

            PanelEditorButton(entry = Entry(type = "battle")) {
                if (it != null) {
                    entries.add(it)
                    ++updateCount
                }
            }
        }
        Divider(
            color = bgColor.value,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}