package spiragps.views.panels

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.createEntry

@Composable
fun BlitzballView(entry: Entry) {
    BasePanelView(title = "BLITZBALL", border = SpiraGPSColours.value.blitzballBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            entry.entries.forEach {
                createEntry(entry = it)

                if (it.trailingBreak) {
                    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
                    Divider(color = bgColour.value, thickness = 15.dp)
                }
            }
        }
    }
}