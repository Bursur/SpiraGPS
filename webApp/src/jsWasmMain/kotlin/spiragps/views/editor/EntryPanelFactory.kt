package spiragps.views.editor

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import spiragps.data.route.Entry

@Composable
fun createEditorPanel(entry: Entry, onUpdate: (Entry) -> Unit) {
    return when(entry.type) {
        "info" -> InfoEditorPanel(entry = entry, onUpdated = onUpdate)
        "image" -> ImageEditorPanel(entry = entry, onUpdated = onUpdate)
        "bullets" -> BulletEditorPanel(entry = entry, onUpdated = onUpdate)
        //"table" ->
        else -> Text("Unknown Editor Type", fontWeight = FontWeight.Bold, color = Color.Red, textDecoration = TextDecoration.Underline)
    }
}