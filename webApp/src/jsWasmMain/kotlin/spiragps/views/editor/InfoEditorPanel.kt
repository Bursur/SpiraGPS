package spiragps.views.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSText

@Composable
fun InfoEditorPanel(entry: Entry, onUpdated: (Entry) -> Unit) {
    var infoText by remember { mutableStateOf(entry.text) }
    var isBold by remember { mutableStateOf(entry.bold) }

    Column {
        // Weight
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Text(text = "Bold:", fontFamily = SpiraGPSText.fontFamily, fontSize = 20.sp)
            Checkbox(checked = isBold, onCheckedChange = {
                isBold = it
                onUpdated(Entry(text = infoText, bold = isBold, type = "info"))
            })
        }

        // Text
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Text:", fontFamily = SpiraGPSText.fontFamily, fontSize = 20.sp)
            TextField(
                value = infoText,
                onValueChange = {
                    infoText = it
                    onUpdated(Entry(text = infoText, bold = isBold, type = "info"))
                },
                textStyle = TextStyle(
                    fontFamily = SpiraGPSText.fontFamily,
                    fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
                ),
                placeholder = {
                    Text(
                        text = "Enter Text...",
                        fontFamily = SpiraGPSText.fontFamily,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}