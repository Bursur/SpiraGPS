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
        TextField(
            value = title,
            onValueChange = {
                title = it
                entry.text = title
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = SpiraGPSColours.text,
                backgroundColor = SpiraGPSColours.infoBackground,
                focusedIndicatorColor = SpiraGPSColours.toggleSelectedTrackColour
            ),
            textStyle = TextStyle(
                fontFamily = SpiraGPSText.fontFamily,
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = "Enter Title...",
                    fontFamily = SpiraGPSText.fontFamily,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )

        // Entries
        key(entries.size) {
            LazyColumn {
                items(entries) {
                    Text("${SpiraGPSText.BULLET_CHAR}  $it", fontFamily = SpiraGPSText.fontFamily)
                }
            }
        }

        Row {
            TextField(
                value = newPoint,
                onValueChange = {
                    newPoint = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = SpiraGPSColours.text,
                    backgroundColor = SpiraGPSColours.infoBackground,
                    focusedIndicatorColor = SpiraGPSColours.toggleSelectedTrackColour
                ),
                textStyle = TextStyle(fontFamily = SpiraGPSText.fontFamily),
                placeholder = {
                    Text(
                        text = "Enter New Point...",
                        fontFamily = SpiraGPSText.fontFamily,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.weight(1f).padding(end = 5.dp)
            )

            TextButton(
                onClick = {
                    entries.add(newPoint)
                    newPoint = ""
                    entry.guide = entries
                }
            ) {
                Text(text = "Add", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = Color.Black))
            }
        }
    }
}