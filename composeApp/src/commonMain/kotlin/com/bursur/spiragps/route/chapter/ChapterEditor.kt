package com.bursur.spiragps.route.chapter

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.ControlPanel
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.title.TitleEditor

@Composable
fun ChapterEditor(chapter: Chapter, conditions: ArrayList<Condition>) {
    var title by remember { mutableStateOf(chapter.title) }
    val entries by remember { mutableStateOf(chapter.entries) }

    var updateCount by remember { mutableStateOf(0) }

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
                        .clickable {
                            selectedEntry = it
                        }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.animateContentSize()
                    ) {
                        if(it == selectedEntry)
                            ControlPanel(
                                entry = it,
                                conditions = conditions,
                                onEntryDeleted = {
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
                                    }
                                },
                                onMoveDown = {
                                    chapter.entries.apply {
                                        val index = indexOf(it)
                                        if(index != -1 && index < size - 1) {
                                            add(index + 1, removeAt(index))
                                            ++updateCount
                                        }
                                    }
                                }
                            )

                        createEntry(entry = it, editor = true, selectedEntry = selectedEntry)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }

        Row {
            EntryEditorButton(entry = Entry(type = "info")) {
                if (it != null) {
                    entries.add(it)
                    ++updateCount
                    selectedEntry = it
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
    }
}