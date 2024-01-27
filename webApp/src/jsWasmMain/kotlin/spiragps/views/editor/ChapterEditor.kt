package spiragps.views.editor

import androidx.compose.foundation.layout.Column
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

@Composable
fun ChapterEditor(chapter: Chapter, onUpdated: (Chapter) -> Unit) {
    var title by remember { mutableStateOf(chapter.title) }
    val entries by remember { mutableStateOf(chapter.entries) }

    var updateCount by remember { mutableStateOf(0) }

    key(updateCount) {
        Column {
            TitleEditor(title) {
                title = it
                onUpdated(Chapter(title = title, entries = entries))
            }

            entries.forEach {
                createEntry(entry = it)
            }

            EntryEditorButton(entry = Entry()) {
                if (it != null) {
                    entries.add(it)
                    ++updateCount
                    onUpdated(Chapter(title = title, entries = entries))
                }
            }
            Divider(
                color = SpiraGPSColours.background,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}