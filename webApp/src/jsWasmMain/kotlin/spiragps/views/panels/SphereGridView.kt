package spiragps.views.panels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.BulletedList

@Composable
fun SphereGridView(entry: Entry) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("TODO: WORK SOMETHING OUT FOR THE SPHERE GRIDS", modifier = Modifier.fillMaxWidth())
        BasePanelView(title = "SPHERE GRID", border = SpiraGPSColours.sphereGridBorder, minimised = entry.minimised) {
            Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
                BulletedList(entry)
            }
        }
    }
}