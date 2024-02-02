package spiragps.views.panels

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.createEntry

@Composable
fun TrialsView(entry: Entry) {
    BasePanelView("CLOISTER OF TRIALS", SpiraGPSColours.value.trialsBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            entry.entries.forEach { entry: Entry ->
                Column {
                    createEntry(entry = entry)

                    if (entry.trailingBreak) {
                        val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
                        Divider(
                            color = bgColour.value,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}