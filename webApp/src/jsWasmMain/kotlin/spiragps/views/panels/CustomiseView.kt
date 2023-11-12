package spiragps.views.panels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.BulletedList

@Composable
fun CustomiseView(entry: Entry) {
    BasePanelView("CUSTOMISE", border = SpiraGPSColours.customiseBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            Text(
                text = entry.item,
                fontWeight = FontWeight.Bold
            )

            BulletedList(entry)
        }
    }
}