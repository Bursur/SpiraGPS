package spiragps.views.panels

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
    BasePanelView("CLOISTER OF TRIALS", SpiraGPSColours.trialsBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            entry.entries.forEach { entry: Entry ->
                Column {
                    createEntry(entry)

                    if (entry.trailingBreak)
                        Divider(
                            color = SpiraGPSColours.infoBackground,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                }
            }
        }
    }
}