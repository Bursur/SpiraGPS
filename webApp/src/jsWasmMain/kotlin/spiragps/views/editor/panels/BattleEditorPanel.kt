package spiragps.views.editor.panels

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit
import spiragps.views.editor.BulletPointEditor

@Composable
fun BattleEditorPanel(entry: Entry) {
    var enemy by remember { mutableStateOf(entry.enemy) }
    var health by remember { mutableStateOf(entry.health) }
    val steps by remember { mutableStateOf(entry.guide) }
    var newStep by remember { mutableStateOf("") }

    var updates by remember { mutableStateOf(0) }

    BasePanelEditor(border = SpiraGPSColours.battleBorder) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "Enemy:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            TextEdit(text = enemy, placeholderText = "Enemy Name...") {
                enemy = it
                entry.enemy = enemy
            }
        }

        Row(
            verticalAlignment =Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "Health:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            TextEdit(
                text = if (health != 0) health.toString() else "",
                placeholderText = "Enemy Health..."
            ) {
                health = try {
                    it.toInt(10)
                } catch (e: Exception) {
                    health
                }

                entry.health = health
            }
        }

        Divider(color = SpiraGPSColours.infoBackground, thickness = 10.dp)
        Text(text = "Steps:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text, modifier = Modifier.padding(horizontal = 5.dp))
        key(updates) {
            steps.forEachIndexed { index, step ->
                BulletPointEditor(
                    text = step,
                    placeholderText = "Update Step...",
                    onUpdated = {
                        steps[index] = it
                        entry.guide = steps
                    },
                    onDeleted = {
                        steps.removeAt(index)
                        entry.guide = steps
                        ++updates
                    }
                )
            }
        }

        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            TextEdit(
                text = newStep,
                placeholderText = "Enter New Step...",
                modifier = Modifier.weight(1f)
            ) { newStep = it }

            TextButton(
                onClick = {
                    steps.add(newStep)
                    newStep = ""
                    entry.guide = steps
                }
            ) {
                Text(text = "Add", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            }
        }
    }
}
