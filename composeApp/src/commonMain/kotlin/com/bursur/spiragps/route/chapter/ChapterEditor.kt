package com.bursur.spiragps.route.chapter

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.EditContextMenu
import com.bursur.spiragps.editor.PanelEditorButton
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.title.TitleEditor
import com.bursur.spiragps.theme.SpiraGPSColours

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChapterEditor(chapter: Chapter, conditions: ArrayList<Condition>) {
    var title by remember { mutableStateOf(chapter.title) }
    val entries by remember { mutableStateOf(chapter.entries) }

    var updateCount by remember { mutableStateOf(0) }
    val bgColor = animateColorAsState(SpiraGPSColours.background)

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
                    Spacer(modifier = Modifier.size(10.dp))

                    if(editControlOpen && selectedEntry == it) {
                        EditContextMenu(
                            open = editControlOpen,
                            entry = it,
                            conditions = conditions,
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
            EntryEditorButton(entry = Entry(type = "info"), conditions = conditions) {
                if (it != null) {
                    entries.add(it)
                    ++updateCount
                }
            }

            PanelEditorButton(entry = Entry(type = "battle"), conditions = conditions) {
                if (it != null) {
                    entries.add(it)
                    ++updateCount
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}