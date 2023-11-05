package spiragps.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.utils.formatWithCommas
import spiragps.style.SpiraGPSColours

@Composable
fun BattleView(name: String, health: Int = 0, points: ArrayList<String>) {
    val title = if(health > 0)
        "$name - ${health.formatWithCommas()} HP"
    else
        name

    BasePanelView(title = title, border = SpiraGPSColours.battleBorder) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(points = points)
        }
    }
}