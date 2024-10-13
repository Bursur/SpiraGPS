package com.bursur.spiragps.components.table

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.highlightKeywords

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
            val bgColour = animateColorAsState(SpiraGPSColours.itemSortBackground[index % 2])
            val textColour = animateColorAsState(SpiraGPSColours.text)
            Text(
                text = highlightKeywords(text),
                fontWeight = SpiraGPSColours.itemSortWeights[index % 2],
                style = SpiraGPSText.typography.info,
                color = textColour.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColour.value)
            )
        }
    }
}