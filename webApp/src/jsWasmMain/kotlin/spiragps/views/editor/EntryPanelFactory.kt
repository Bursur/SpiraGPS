package spiragps.views.editor

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSText
import spiragps.views.editor.panels.BattleEditorPanel

@Composable
fun createEditorPanel(entry: Entry) {
    return when(entry.type) {
        "info" -> InfoEditorPanel(entry = entry)
        "image" -> ImageEditorPanel(entry = entry)
        "bullets" -> BulletEditorPanel(entry = entry)
        "table" -> TableEditorPanel(entry = entry)
        "battle" -> BattleEditorPanel(entry = entry)
        else -> Text("Select an Entry type", fontFamily = SpiraGPSText.fontFamily, fontWeight = FontWeight.Bold)
    }
}