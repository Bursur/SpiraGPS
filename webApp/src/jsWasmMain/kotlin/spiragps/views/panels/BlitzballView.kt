package spiragps.views.panels

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
import spiragps.views.BulletedList
import spiragps.views.createEntry

@Composable
fun BlitzballView(entry:Entry) {
    BasePanelView(title = "BLITZBALL", border = SpiraGPSColours.blitzballBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            entry.entries.forEach {
                createEntry(it)

                if (it.trailingBreak)
                    Divider(color = SpiraGPSColours.infoBackground, thickness = 15.dp)
            }
        }
    }
}