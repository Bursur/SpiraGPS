package com.bursur.spiragps.editor

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.backbutton.BackButton
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.route.chapter.ChapterEditor
import com.bursur.spiragps.route.conditions.ConditionEditor
import com.bursur.spiragps.route.conditions.getConditionString
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.keywords.KeywordsEditorButton
import com.bursur.spiragps.route.title.TitleEditor
import com.bursur.spiragps.spherecounter.SphereCounterEditor
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.utils.FileService
import com.seiko.imageloader.rememberImagePainter
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

var selectedEntry by mutableStateOf(Entry())
var secondaryEntry by mutableStateOf(Entry())

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditorPage(navigationState: NavigationState) {
    val editorState = rememberEditorState()
    var route by remember { mutableStateOf(Route()) }
    val scrollState = rememberLazyListState()

    var title by remember { mutableStateOf(route.title) }
    var conditionDialogOpen by remember { mutableStateOf(false) }
    var sphereDialogOpen by remember { mutableStateOf(false) }

    val infoBgColor by animateColorAsState(SpiraGPSColours.infoBackground)
    val textColour by animateColorAsState(SpiraGPSColours.text)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(remember { MutableInteractionSource() }, null) { selectedEntry = Entry() }
    ) {
        Row {
            // Contents
            key(editorState.chapterCount, editorState.chapterName) {
                Column(modifier = Modifier.width(150.dp).padding(10.dp)) {
                    BackButton(
                        navigationState = navigationState,
                        modifier = Modifier.padding(all = 10.dp)
                    )

                    LazyColumn {
                        itemsIndexed(route.chapters) { index, item ->
                            val scope = rememberCoroutineScope()
                            Text(
                                text = item.title,
                                style = SpiraGPSText.typography.contentsEntry,
                                color = textColour,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .width(150.dp)
                                    .padding(vertical = 5.dp)
                                    .clickable {
                                        scope.launch {
                                            scrollState.animateScrollToItem(route.introduction.entries.size + 4 + index)
                                        }
                                    }
                            )
                        }
                    }
                }
            }

            key(editorState.updateCounter) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Route
                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier
                            .fillMaxWidth(.75f)
                            .padding(top = 25.dp)
                    ) {
                        // Title
                        item {
                            TitleEditor(title = title) {
                                title = it
                                route.title = it
                            }
                            HorizontalDivider(
                                color = infoBgColor,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }

                        stickyHeader { StickyHeader("Introduction") }

                        // Intro
                        items(route.introduction.entries) {
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
                                    if (it == selectedEntry)
                                        ControlPanel(
                                            entry = it,
                                            conditions = route.conditions,
                                            onEntryDeleted = {
                                                if (route.introduction.entries.remove(it))
                                                    ++editorState.updateCounter
                                            },
                                            onMoveUp = {
                                                route.introduction.entries.apply {
                                                    val index = indexOf(it)
                                                    if (index >= 1) {
                                                        add(index - 1, removeAt(index))
                                                        ++editorState.updateCounter
                                                    }
                                                }
                                            },
                                            onMoveDown = {
                                                route.introduction.entries.apply {
                                                    val index = indexOf(it)
                                                    if (index != -1 && index < size - 1) {
                                                        add(index + 1, removeAt(index))
                                                        ++editorState.updateCounter
                                                    }
                                                }
                                            }
                                        )

                                    createEntry(
                                        entry = it,
                                        editor = true,
                                        selectedEntry = selectedEntry
                                    )
                                }

                                if (it.requirement.isNotEmpty()) {
                                    val tooltipPosition =
                                        TooltipDefaults.rememberPlainTooltipPositionProvider()
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
                                        Image(
                                            rememberImagePainter("https://bursur.github.io/SpiraGPS/condition_arrow.png"),
                                            contentDescription = "",
                                            colorFilter = ColorFilter.tint(textColour),
                                            modifier = Modifier
                                                .sizeIn(maxWidth = 20.dp, maxHeight = 20.dp)
                                                .align(Alignment.TopStart)
                                        )
                                    }
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
                            HorizontalDivider(
                                color = infoBgColor,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }

                        // Chapters
                        stickyHeader { StickyHeader("Chapters") }

                        items(route.chapters) {
                            ChapterEditor(it, route.conditions, editorState)
                        }

                        item {
                            TextButton(onClick = {
                                route.chapters.add(Chapter(index = route.chapters.size))
                                ++editorState.updateCounter
                                ++editorState.chapterCount
                            }) {
                                Text(
                                    text = "Add Chapter",
                                    style = SpiraGPSText.typography.info,
                                    color = SpiraGPSColours.text
                                )
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(editorState.chapterCount) {
            scrollState.animateScrollToItem(route.introduction.entries.size + route.chapters.size + 5)
        }

        ControlPanel(
            route = route,
            onSave = {
                scope.launch {
                    val data = Json.encodeToString(route)
                    FileKit.saveFile(
                        baseName = route.title.ifEmpty { "Untitled Guide" },
                        extension = "json",
                        initialDirectory = "",
                        bytes = data.encodeToByteArray()
                    )
                }
            },
            onLoad = {
                scope.launch {
                    FileKit.pickFile(
                        type = PickerType.File(extensions = listOf("json")),
                        mode = PickerMode.Single,
                        title = "Select a Destination",
                        initialDirectory = ""
                    )?.let { file ->
                        val data = file.readBytes().decodeToString()
                        Json.decodeFromString<Route>(data).let {
                            SpiraGPSText.addKeywords(it.keywords)
                            route = it
                            title = route.title
                            ++editorState.updateCounter
                        }
                    }
                }
            },
            onModifyConditions = { conditionDialogOpen = true },
            modifier = Modifier.align(Alignment.BottomCenter),
            onKeywordsUpdated = { ++editorState.updateCounter },
            onSetSphereRequirement = { sphereDialogOpen = true }
        )
    }

    if(conditionDialogOpen) {
        ConditionEditor(route) { conditionDialogOpen = false }
    }

    if(sphereDialogOpen) {
        SphereCounterEditor(route) { sphereDialogOpen = false }
    }
}

@Composable
private fun StickyHeader(title: String) {
    val bgColor by animateColorAsState(SpiraGPSColours.background)
    Text(
        text = title,
        fontFamily = SpiraGPSText.fontFamily,
        color = SpiraGPSColours.text,
        modifier = Modifier.fillMaxWidth().background(bgColor).alpha(.5f)
    )
}

@Composable
private fun ControlPanel(
    modifier: Modifier = Modifier,
    route: Route, onSave: () -> Unit,
    onLoad: () -> Unit,
    onModifyConditions: () -> Unit,
    onKeywordsUpdated: () -> Unit,
    onSetSphereRequirement: () -> Unit
) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    val bgColour by animateColorAsState(SpiraGPSColours.editorControlBackground)

    Row(modifier = modifier.padding(10.dp).background(bgColour, RoundedCornerShape(10.dp))) {
        TextButton(onClick = { onSave() }, modifier = Modifier.padding(end = 5.dp)) {
            Text(text = "Save Route", style = SpiraGPSText.typography.info, color = textColour)
        }

        TextButton(onClick = { onLoad() }) {
            Text(text = "Load Route", style = SpiraGPSText.typography.info, color = textColour)
        }

        TextButton(onClick = { onModifyConditions() }) {
            Text(text = "Update Conditions", style = SpiraGPSText.typography.info, color = textColour)
        }

        TextButton(onClick = { onSetSphereRequirement() }) {
            Text(text = "Set Spheres", style = SpiraGPSText.typography.info, color = textColour)
        }

        KeywordsEditorButton(route = route, onDismiss = { onKeywordsUpdated() })
    }
}