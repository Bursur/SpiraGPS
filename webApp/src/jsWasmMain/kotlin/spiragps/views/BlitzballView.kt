package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.Entry
import spiragps.utils.highlightKeywords
import spiragps.style.SpiraGPSColours

@Composable
fun BlitzballView(actions: ArrayList<Entry>) {
    BasePanelView(title = "BLITZBALL", border = SpiraGPSColours.blitzballBorder) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            actions.forEach {
                when (it.type) {
                    "info" -> Text(
                        text = highlightKeywords(it.text),
                        fontWeight = if (it.bold) FontWeight.Bold else FontWeight.Normal
                    )

                    "bullets" -> BulletedList(points = it.guide)
                }

                if (it.trailingBreak)
                    Divider(color = SpiraGPSColours.infoBackground, thickness = 15.dp)
            }
        }
    }
}