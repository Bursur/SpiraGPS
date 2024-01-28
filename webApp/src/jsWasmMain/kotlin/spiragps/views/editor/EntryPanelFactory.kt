package spiragps.views.editor

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSText
import spiragps.views.editor.panels.BattleEditorPanel
import spiragps.views.editor.panels.BlitzballEditorPanel
import spiragps.views.editor.panels.CustomisationEditorPanel
import spiragps.views.editor.panels.EncounterEditorPanel
import spiragps.views.editor.panels.EquipmentEditorPanel
import spiragps.views.editor.panels.ItemSortEditorPanel
import spiragps.views.editor.panels.ShopEditorPanel
import spiragps.views.editor.panels.SpheregridEditorPanel
import spiragps.views.editor.panels.TipEditorPanel
import spiragps.views.editor.panels.TrialEditorPanel

@Composable
fun createEditorPanel(entry: Entry) {
    return when(entry.type) {
        "info" -> InfoEditorPanel(entry = entry)
        "image" -> ImageEditorPanel(entry = entry)
        "bullets" -> BulletEditorPanel(entry = entry)
        "table" -> TableEditorPanel(entry = entry)
        "battle" -> BattleEditorPanel(entry = entry)
        "encounter" -> EncounterEditorPanel(entry = entry)
        "trial" -> TrialEditorPanel(entry = entry)
        "shop" -> ShopEditorPanel(entry = entry)
        "equipment" -> EquipmentEditorPanel(entry = entry)
        "itemsort" -> ItemSortEditorPanel(entry = entry)
        "spheregrid" -> SpheregridEditorPanel(entry = entry)
        "customise" -> CustomisationEditorPanel(entry = entry)
        "blitzball" -> BlitzballEditorPanel(entry = entry)
        "tip" -> TipEditorPanel(entry = entry)
        else -> Text("Select an Entry type", fontFamily = SpiraGPSText.fontFamily, fontWeight = FontWeight.Bold)
    }
}