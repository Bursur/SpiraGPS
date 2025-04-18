package com.bursur.spiragps.route.chapter

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ForkRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.ControlPanel
import com.bursur.spiragps.editor.EditorState
import com.bursur.spiragps.editor.selectedEntry
import com.bursur.spiragps.route.conditions.getConditionString
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.title.TitleEditor
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.seiko.imageloader.rememberImagePainter
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChapterEditor(chapter: Chapter, conditions: ArrayList<Condition>, editorState: EditorState) {
    var title by remember { mutableStateOf(chapter.title) }
    val entries by remember { mutableStateOf(chapter.entries) }
    val textColour by animateColorAsState(SpiraGPSColours.text)

    var updateCount by remember { mutableStateOf(0) }

    Column {
        TitleEditor(title) {
            title = it
            chapter.title = title
            ++editorState.chapterName
        }

        key(updateCount) {
            entries.forEach {
                Box(
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
                                },
                                onDuplicate = {
                                    val data = Json.encodeToString<Entry>(it)
                                    val newEntry = Json.decodeFromString<Entry>(data).apply {
                                        id = Clock.System.now().epochSeconds
                                    }

                                    chapter.entries.add(newEntry)
                                    ++editorState.updateCounter
                                    selectedEntry = newEntry
                                }
                            )

                        createEntry(entry = it, editor = true, selectedEntry = selectedEntry)
                        Spacer(modifier = Modifier.size(10.dp))
                    }

                    if(it.requirement.isNotEmpty()) {
                        val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
                        val tooltipState = rememberBasicTooltipState()
                        BasicTooltipBox(
                            positionProvider = tooltipPosition,
                            state = tooltipState,
                            tooltip = {
                                Surface(
                                    shadowElevation = 5.dp,
                                    shape = RoundedCornerShape(5.dp),
                                    color = SpiraGPSColours.background,
                                    modifier = Modifier.padding(2.dp)
                                ) {
                                    Text(
                                        text = getConditionString(it),
                                        style = SpiraGPSText.typography.info,
                                        color = SpiraGPSColours.text
                                    )
                                }
                            },
                            modifier = Modifier.offset(x = (-20).dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ForkRight,
                                contentDescription = "",
                                tint = textColour,
                                modifier = Modifier
                                    .sizeIn(maxWidth = 20.dp, maxHeight = 20.dp)
                                    .align(Alignment.TopStart)
                            )
                        }
                    }
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