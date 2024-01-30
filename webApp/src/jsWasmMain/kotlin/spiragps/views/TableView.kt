package spiragps.views

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
            Text(
                text = text,
                fontWeight = SpiraGPSColours.itemSortWeights[index % 2],
                style = SpiraGPSText.typography.info,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SpiraGPSColours.itemSortBackground[index % 2])
            )
        }
    }
}