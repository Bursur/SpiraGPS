package spiragps.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.Chapter
import spiragps.data.ContentsState

@Composable
fun ContentsView(modifier: Modifier = Modifier, chapters: ArrayList<Chapter>, contentsState: ContentsState) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.padding(10.dp)
    ) {
        // Title
        Text(text = "Chapters:", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Column(
            modifier = Modifier
                .scrollable(state = scrollState, orientation = Orientation.Vertical, enabled = true)
        ) {
            // Chapters
            chapters.forEach {
                Text(
                    text = it.title,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            contentsState.selectedChapter = it.title
                        }
                )
            }
        }
    }
}