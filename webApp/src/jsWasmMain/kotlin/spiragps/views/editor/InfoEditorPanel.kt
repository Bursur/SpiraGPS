package spiragps.views.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
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
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@Composable
fun InfoEditorPanel(entry: Entry) {
    var infoText by remember { mutableStateOf(entry.text) }
    var isBold by remember { mutableStateOf(entry.bold) }

    Column {
        // Weight
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Text(text = "Bold:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            Checkbox(
                checked = isBold,
                onCheckedChange = {
                    isBold = it
                    entry.bold = isBold
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = SpiraGPSColours.toggleUnselectedTrackColour,
                    checkedColor = SpiraGPSColours.toggleSelectedTrackColour
                )
            )
        }

        // Text
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Text:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            TextEdit(text = infoText, placeholderText = "Enter Text...", isBold = isBold) {
                infoText = it
                entry.text = infoText
            }
        }
    }
}