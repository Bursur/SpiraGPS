package spiragps.views.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@Composable
fun BulletEditorPanel(entry: Entry) {
    val entries: ArrayList<String> by remember { mutableStateOf(entry.guide) }
    var newPoint by remember { mutableStateOf("") }
    var isBold by remember { mutableStateOf(entry.bold) }
    var title by remember { mutableStateOf(entry.text) }

    Column {
        // Weight
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Text(text = "Bold:", fontFamily = SpiraGPSText.fontFamily, fontSize = 20.sp)
            Checkbox(checked = isBold, onCheckedChange = {
                isBold = it
                entry.bold = isBold
            })
        }

        // Title
        TextEdit(text = title, placeholderText = "Enter Title...", isBold = isBold) {
            title = it
            entry.text = title
        }

        // Entries
        key(entries.size) {
            LazyColumn {
                items(entries) {
                    Text("${SpiraGPSText.BULLET_CHAR}  $it", fontFamily = SpiraGPSText.fontFamily)
                }
            }
        }

        Row {
            TextEdit(
                text = newPoint,
                placeholderText = "Enter New Point...",
                modifier = Modifier.weight(1f)
            ) { newPoint = it }

            TextButton(
                onClick = {
                    entries.add(newPoint)
                    newPoint = ""
                    entry.guide = entries
                }
            ) {
                Text(text = "Add",style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
            }
        }
    }
}