package spiragps.views.editor.panels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.components.TextEdit

@Composable
fun BattleEditorPanel(entry: Entry) {
    var enemy by remember { mutableStateOf(entry.enemy) }
    var health by remember { mutableStateOf(entry.health) }
    var steps by remember { mutableStateOf(entry.guide) }

    Surface(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, SpiraGPSColours.battleBorder),
        color = SpiraGPSColours.infoBackground,
    ) {
        Column {
            Divider(color = SpiraGPSColours.battleBorder, thickness = 20.dp)
            TextEdit(text = enemy, placeholderText = "Enemy Name...") {
                enemy = it
                entry.enemy = enemy
            }
            TextEdit(text = if(health != 0) health.toString() else "", placeholderText = "Enemy Health...") {
                health = try {
                    it.toInt(10)
                } catch(e: Exception) {
                    health
                }

                entry.health = health
            }
        }
    }
}