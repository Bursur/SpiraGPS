package spiragps.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import spiragps.data.route.Chapter
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.BackButton
import spiragps.views.createEntry
import spiragps.views.editor.ChapterEditor
import spiragps.views.editor.EntryEditorButton
import spiragps.views.editor.TitleEditor
import spiragps.views.editor.createEditorPanel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditorPage(navigationState: NavigationState) {
    val editorState = rememberEditorState()
    val route by remember { mutableStateOf(Route()) }

    var title by remember { mutableStateOf(route.title) }

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
                    Divider(color = SpiraGPSColours.infoBackground, modifier = Modifier.padding(vertical = 10.dp))
                }

                stickyHeader { StickyHeader("Introduction") }

                // Intro
                items(route.introduction.entries) {
                    createEntry(entry = it)
                }

                item {
                    EntryEditorButton(entry = Entry()) {
                        if (it != null) {
                            route.introduction.entries.add(it)
                            ++editorState.updateCounter
                        }
                    }
                    Divider(color = SpiraGPSColours.infoBackground, modifier = Modifier.padding(vertical = 10.dp))
                }

                // Chapters
                stickyHeader { StickyHeader("Chapters") }

                items(route.chapters) {
                    ChapterEditor(it) {

                    }
                }

                item {
                    TextButton(onClick = {
                        route.chapters.add(Chapter(index = route.chapters.size))
                        ++editorState.updateCounter
                    }) {
                        Text(text = "Add Chapter", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = SpiraGPSColours.text))
                    }
                }
            }
        }
    }
}

@Composable
private fun StickyHeader(title: String) {
    Text(
        text = title,
        fontFamily = SpiraGPSText.fontFamily,
        modifier = Modifier.fillMaxWidth().background(SpiraGPSColours.background).alpha(.5f)
    )
}

