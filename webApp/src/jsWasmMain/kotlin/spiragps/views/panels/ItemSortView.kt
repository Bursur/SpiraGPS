package spiragps.views.panels

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.utils.highlightKeywords

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
    val bgColour = animateColorAsState(background)
    val textColour = animateColorAsState(SpiraGPSColours.text)

    Row(modifier = Modifier.background(bgColour.value).fillMaxWidth()) {
        Text(text = instruction[0], style = SpiraGPSText.typography.info, fontWeight = weight, color = textColour.value, modifier = Modifier.fillMaxWidth(.35f).padding(4.dp))
        if(instruction.size > 1)
            Text(text = highlightKeywords(instruction[1]), style = SpiraGPSText.typography.info, fontWeight = weight, color = textColour.value, modifier = Modifier.fillMaxWidth(.65f).padding(4.dp))
    }
}