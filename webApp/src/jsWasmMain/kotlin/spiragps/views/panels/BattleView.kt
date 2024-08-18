package spiragps.views.panels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.utils.formatWithCommas
import spiragps.style.SpiraGPSColours
import spiragps.views.BulletedList

@Composable
fun BattleView(entry: Entry) {
    val title = if(entry.health > 0)
        "${entry.enemy} - ${entry.health.formatWithCommas()} HP"
    else
        entry.enemy

    BasePanelView(title = title, border = SpiraGPSColours.battleBorder, minimised = entry.minimised) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(entry)
        }
    }
}