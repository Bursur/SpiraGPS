package spiragps.views

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
import spiragps.style.SpiraGPSColours

@Composable
fun ItemSortView(items: ArrayList<String>) {
    BasePanelView(title = "ITEM SORT", border = SpiraGPSColours.itemSortBorder) {
        Column(
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 20.dp)
        ) {
            items.forEachIndexed { index, it ->
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
    Row(modifier = Modifier.background(background).fillMaxWidth()) {
        Text(text = instruction[0], fontWeight = weight, modifier = Modifier.fillMaxWidth(.35f).padding(4.dp))
        Text(text = instruction[1], fontWeight = weight, modifier = Modifier.fillMaxWidth(.65f).padding(4.dp))
    }
}