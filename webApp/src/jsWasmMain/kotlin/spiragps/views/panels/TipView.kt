package spiragps.views.panels

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
fun TipView(entry: Entry) {
    BasePanelView(title = "TIP", border = SpiraGPSColours.value.tipBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            entry.entries.forEach {
                createEntry(entry = it)

                if (it.trailingBreak)
                    Divider(color = SpiraGPSColours.value.infoBackground, thickness = 15.dp)
            }
        }
    }
}