package com.bursur.spiragps.editor

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.backbutton.BackButton
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.route.chapter.ChapterEditor
import com.bursur.spiragps.route.conditions.ConditionEditor
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.keywords.KeywordsEditorButton
import com.bursur.spiragps.route.title.TitleEditor
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.utils.FileService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditorPage(navigationState: NavigationState) {
    val editorState = rememberEditorState()
    var route by remember { mutableStateOf(Route()) }

    var title by remember { mutableStateOf(route.title) }
    var conditionDialogOpen by remember { mutableStateOf(false) }
    var selectedEntry by remember { mutableStateOf(Entry()) }

    val infoBgColor = animateColorAsState(SpiraGPSColours.infoBackground)
    var awaitingData by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        BackButton(
            navigationState = navigationState,
            modifier = Modifier.align(Alignment.TopStart).padding(all = 10.dp)
        )

        key(editorState.updateCounter) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .align(Alignment.TopCenter)
                    .padding(top = 25.dp)
            ) {
                // Title
                item {
                    TitleEditor(title = title) {
                        title = it
                        route.title = it
                    }
                    HorizontalDivider(color = infoBgColor.value, modifier = Modifier.padding(vertical = 10.dp))
                }

                stickyHeader { StickyHeader("Introduction") }

                // Intro
                items(route.introduction.entries) {
                    Row(
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
                                    conditions = route.conditions,
                                    onEntryDeleted = {
                                        if(route.introduction.entries.remove(it))
                                            ++editorState.updateCounter
                                    },
                                    onMoveUp = {
                                        route.introduction.entries.apply {
                                            val index = indexOf(it)
                                            if(index >= 1) {
                                                add(index - 1, removeAt(index))
                                                ++editorState.updateCounter
                                            }
                                        }
                                    },
                                    onMoveDown = {
                                        route.introduction.entries.apply {
                                            val index = indexOf(it)
                                            if(index != -1 && index < size - 1) {
                                                add(index + 1, removeAt(index))
                                                ++editorState.updateCounter
                                            }
                                        }
                                    }
                                )

                            createEntry(entry = it, editor = true, selectedEntry = selectedEntry)
                        }
                    }
                }

                item {
                    EntryEditorButton(entry = Entry(type = "info")) {
                        if (it != null) {
                            route.introduction.entries.add(it)
                            ++editorState.updateCounter
                            selectedEntry = it
                        }
                    }
                    HorizontalDivider(color = infoBgColor.value, modifier = Modifier.padding(vertical = 10.dp))
                }

                // Chapters
                stickyHeader { StickyHeader("Chapters") }

                items(route.chapters) {
                    ChapterEditor(it, route.conditions)
                }

                item {
                    TextButton(onClick = {
                        route.chapters.add(Chapter(index = route.chapters.size))
                        ++editorState.updateCounter
                    }) {
                        Text(text = "Add Chapter", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                    }
                }
            }
        }

        ControlPanel(
            route = route,
            onSave = {
                val data = Json.encodeToString(route)
                FileService.saveFile(data)
            },
            onLoad = { awaitingData = true },
            onModifyConditions = { conditionDialogOpen = true },
            modifier = Modifier.align(Alignment.BottomCenter),
            onKeywordsUpdated = { ++editorState.updateCounter }
        )
    }

    if(conditionDialogOpen) {
        ConditionEditor(route) { conditionDialogOpen = false }
    }

    // Load in the saved
    LaunchedEffect(awaitingData) {
        if(awaitingData) {
            val data = FileService.loadFile()

            awaitingData = false

            if(data != "cancelled") {
                println(data)
                Json.decodeFromString<Route>(data).let {
                    SpiraGPSText.addKeywords(it.keywords)
                    route = it
                    title = route.title
                    ++editorState.updateCounter
                }
            }
        }
    }
}

@Composable
private fun StickyHeader(title: String) {
    val bgColor = animateColorAsState(SpiraGPSColours.background)
    Text(
        text = title,
        fontFamily = SpiraGPSText.fontFamily,
        color = SpiraGPSColours.text,
        modifier = Modifier.fillMaxWidth().background(bgColor.value).alpha(.5f)
    )
}

@Composable
private fun ControlPanel(modifier: Modifier = Modifier, route: Route, onSave: () -> Unit, onLoad: () -> Unit, onModifyConditions: () -> Unit, onKeywordsUpdated: () -> Unit) {
    val textColour = animateColorAsState(SpiraGPSColours.text)
    val bgColour = animateColorAsState(SpiraGPSColours.editorControlBackground)

    Row(modifier = modifier.padding(10.dp).background(bgColour.value, RoundedCornerShape(10.dp))) {
        TextButton(onClick = { onSave() }, modifier = Modifier.padding(end = 5.dp)) {
            Text(text = "Save Route", style = SpiraGPSText.typography.info, color = textColour.value)
        }

        TextButton(onClick = { onLoad() }) {
            Text(text = "Load Route", style = SpiraGPSText.typography.info, color = textColour.value)
        }

        TextButton(onClick = { onModifyConditions() }) {
            Text(text = "Update Conditions", style = SpiraGPSText.typography.info, color = textColour.value)
        }

        KeywordsEditorButton(route = route, onDismiss = { onKeywordsUpdated() })
    }
}