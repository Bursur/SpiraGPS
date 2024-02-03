package spiragps.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.utils.highlightKeywords

@Composable
fun TableView(entry: Entry) {
    Row(modifier = Modifier.fillMaxWidth()) {
        var start = 0
        val columnSize = entry.guide.size / entry.columns

        for(i in 0 until entry.columns) {
            TableColumn(
                entries = entry.guide.slice(IntRange(start, kotlin.math.min(start + (columnSize - 1), entry.guide.size - 1))),
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            start += columnSize
        }
    }
}

@Composable
fun TableColumn(modifier: Modifier = Modifier, entries: List<String>) {
    Column(modifier = modifier) {
        entries.forEachIndexed { index, text ->
            val bgColour = animateColorAsState(SpiraGPSColours.value.itemSortBackground[index % 2])
            val textColour = animateColorAsState(SpiraGPSColours.value.text)
            Text(
                text = highlightKeywords(text),
                fontWeight = SpiraGPSColours.value.itemSortWeights[index % 2],
                style = SpiraGPSText.typography.value.info,
                color = textColour.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColour.value)
            )
        }
    }
}