package spiragps.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours

@Composable
fun EquipmentView(items: ArrayList<String>) {
    BasePanelView(title = "EQUIPMENT", border = SpiraGPSColours.equipmentBorder) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(points = items)
        }
    }
}