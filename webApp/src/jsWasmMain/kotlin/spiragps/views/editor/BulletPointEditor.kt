package spiragps.views.editor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@Composable
fun BulletPointEditor(text: String, placeholderText: String = "", onUpdated: (String) -> Unit, onDeleted: () -> Unit) {
    var newText by remember { mutableStateOf(text) }

    Row(modifier = Modifier.padding(horizontal = 5.dp)) {
        TextEdit(
            text = newText,
            placeholderText = placeholderText,
            modifier = Modifier.weight(1f).padding(end = 5.dp)
        ) {
            newText = it
            onUpdated(newText)
        }

        TextButton(
            onClick = { onDeleted() }
        ) {
            Text(text = "X", style = SpiraGPSText.typography.info)
        }
    }
}