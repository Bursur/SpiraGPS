package spiragps.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours

@Composable
fun SphereGridView(spheres: ArrayList<String>) {
    Column {
        Text("TODO: WORK SOMETHING OUT FOR THE SPHERE GRIDS")
        BasePanelView(title = "SPHERE GRID", border = SpiraGPSColours.sphereGridBorder) {
            Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
                BulletedList(points = spheres)
            }
        }
    }
}