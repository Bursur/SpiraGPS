package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.Chapter
import spiragps.data.Entry
import spiragps.style.SpiraGPSColours

@Composable
fun ChapterView(chapter: Chapter) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TitleView(chapter.title)
        Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))

        chapter.entries.forEach { entry: Entry ->
            createEntry(entry)

            if(entry.trailingBreak)
                Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))
        }
    }
}