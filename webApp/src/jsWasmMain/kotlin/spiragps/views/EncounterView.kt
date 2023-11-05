package spiragps.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours

@Composable
fun EncounterView(enemies: ArrayList<String>) {
    BasePanelView(title = "ENCOUNTERS", border = SpiraGPSColours.encounterBorder) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(points = enemies)
        }
    }
}