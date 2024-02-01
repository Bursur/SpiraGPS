package spiragps.views.editor

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
import spiragps.views.createEntry
import spiragps.views.editor.panels.PanelEditorButton

@Composable
fun ChapterEditor(chapter: Chapter) {
    var title by remember { mutableStateOf(chapter.title) }
    val entries by remember { mutableStateOf(chapter.entries) }

    var updateCount by remember { mutableStateOf(0) }

    Column {
        TitleEditor(title) {
            title = it
            chapter.title = title
        }

        key(updateCount) {
            entries.forEach {
                createEntry(entry = it)
                Divider(color = SpiraGPSColours.value.background, thickness = 10.dp)
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
            color = SpiraGPSColours.value.background,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}