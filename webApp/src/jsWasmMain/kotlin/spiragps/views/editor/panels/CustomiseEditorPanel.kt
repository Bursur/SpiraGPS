package spiragps.views.editor.panels

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun CustomisationEditorPanel(entry: Entry) {
    var item by remember { mutableStateOf(entry.item) }
    val steps by remember { mutableStateOf(entry.guide) }
    var newStep by remember { mutableStateOf("") }
    var updates by remember { mutableStateOf(0) }

    BasePanelEditor(border = SpiraGPSColours.customiseBorder) {
        Row(
            verticalAlignment =Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "Item:", fontFamily = SpiraGPSText.fontFamily)
            TextEdit(text = item, placeholderText = "Item Name...") {
                item = it
                entry.item = item
            }
        }

        Text(text = "Customisations:", fontFamily = SpiraGPSText.fontFamily, modifier = Modifier.padding(horizontal = 5.dp))
        key(updates) {
            steps.forEachIndexed { index, step ->
                BulletPointEditor(
                    text = step,
                    placeholderText = "Update Customisation...",
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
                placeholderText = "Enter New Customisation...",
                modifier = Modifier.weight(1f)
            ) { newStep = it }

            TextButton(
                onClick = {
                    steps.add(newStep)
                    newStep = ""
                    entry.guide = steps
                }
            ) {
                Text(text = "Add", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = Color.Black))
            }
        }
    }
}