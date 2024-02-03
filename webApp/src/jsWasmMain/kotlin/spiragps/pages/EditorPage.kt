package spiragps.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import spiragps.data.editor.rememberEditorState
import spiragps.data.route.NavigationState
import spiragps.data.route.Route
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import spiragps.data.route.Chapter
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.BackButton
import spiragps.views.components.EditContextMenu
import spiragps.views.createEntry
import spiragps.views.editor.ChapterEditor
import spiragps.views.editor.EntryEditorButton
import spiragps.views.editor.TitleEditor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditorPage(navigationState: NavigationState) {
    val editorState = rememberEditorState()
    val route by remember { mutableStateOf(Route()) }

    var title by remember { mutableStateOf(route.title) }
    var editControlOpen by remember { mutableStateOf(false) }
    var selectedEntry by remember { mutableStateOf(Entry()) }

    val infoBgColor = animateColorAsState(SpiraGPSColours.value.infoBackground)

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
                    Divider(color = infoBgColor.value, modifier = Modifier.padding(vertical = 10.dp))
                }

                stickyHeader { StickyHeader("Introduction") }

                // Intro
                items(route.introduction.entries) {
                    Row(
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

                        if(editControlOpen && selectedEntry == it) {
                            EditContextMenu(
                                open = editControlOpen,
                                entry = it,
                                onDismiss = { editControlOpen = false },
                                onEntryUpdated = {
                                    editControlOpen = false
                                    ++editorState.updateCounter
                                },
                                onEntryDeleted = {
                                    editControlOpen = false
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
                                        editControlOpen = false
                                    }
                                },
                                onMoveDown = {
                                    route.introduction.entries.apply {
                                        val index = indexOf(it)
                                        if(index != -1 && index < size - 1) {
                                            add(index + 1, removeAt(index))
                                            ++editorState.updateCounter
                                        }
                                        editControlOpen = false
                                    }
                                }
                            )
                        }
                    }
                }

                item {
                    EntryEditorButton(entry = Entry(type = "info")) {
                        if (it != null) {
                            route.introduction.entries.add(it)
                            ++editorState.updateCounter
                        }
                    }
                    Divider(color = infoBgColor.value, modifier = Modifier.padding(vertical = 10.dp))
                }

                // Chapters
                stickyHeader { StickyHeader("Chapters") }

                items(route.chapters) {
                    ChapterEditor(it)
                }

                item {
                    TextButton(onClick = {
                        route.chapters.add(Chapter(index = route.chapters.size))
                        ++editorState.updateCounter
                    }) {
                        Text(text = "Add Chapter", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
                    }
                }
            }
        }
    }
}

@Composable
private fun StickyHeader(title: String) {
    val bgColor = animateColorAsState(SpiraGPSColours.value.background)
    Text(
        text = title,
        fontFamily = SpiraGPSText.fontFamily,
        color = SpiraGPSColours.value.text,
        modifier = Modifier.fillMaxWidth().background(bgColor.value).alpha(.5f)
    )
}

