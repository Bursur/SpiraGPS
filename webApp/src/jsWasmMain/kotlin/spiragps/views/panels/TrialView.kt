package spiragps.views.panels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.BulletedList

@Composable
fun TrialsView(entry: Entry) {
    BasePanelView("CLOISTER OF TRIALS", SpiraGPSColours.trialsBorder, minimised = entry.minimised) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(entry)
        }
    }
}