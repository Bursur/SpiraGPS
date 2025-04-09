package com.bursur.spiragps.route.entries.itemsort

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.BasePanelView
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.highlightKeywords

@Composable
fun ItemSortView(entry: Entry) {
    BasePanelView(title = "ITEM SORT", border = SpiraGPSColours.itemSortBorder, minimised = entry.minimised) {
        Column(
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 20.dp)
        ) {
            entry.guide.forEachIndexed { index, it ->
                Item(
                    name = it,
                    background = SpiraGPSColours.itemSortBackground[index % 2],
                    weight = SpiraGPSColours.itemSortWeights[index % 2]
                )
            }
        }
    }
}

@Composable
private fun Item(name: String, background: Color, weight: FontWeight) {
    val instruction = name.split(",")
    val bgColour by animateColorAsState(background)
    val textColour by animateColorAsState(SpiraGPSColours.text)

    Row(modifier = Modifier.background(bgColour).fillMaxWidth()) {
        Text(text = instruction[0], style = SpiraGPSText.typography.info, fontWeight = weight, color = textColour, modifier = Modifier.fillMaxWidth(.35f).padding(4.dp))
        if(instruction.size > 1)
            Text(text = highlightKeywords(instruction[1]), style = SpiraGPSText.typography.info, fontWeight = weight, color = textColour, modifier = Modifier.fillMaxWidth(.65f).padding(4.dp))
    }
}