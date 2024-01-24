package spiragps.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import spiragps.data.route.Entry
import spiragps.views.panels.BattleView
import spiragps.views.panels.BlitzballView
import spiragps.views.panels.CustomiseView
import spiragps.views.panels.EncounterView
import spiragps.views.panels.EquipmentView
import spiragps.views.panels.ItemSortView
import spiragps.views.panels.ShopView
import spiragps.views.panels.SphereGridView
import spiragps.views.panels.TrialsView

@Composable
fun createEntry(modifier: Modifier = Modifier, entry: Entry) {
    return when(entry.type) {
        "info" -> InfoView(entry = entry, modifier = modifier)
        "battle" -> BattleView(entry)
        "encounter" -> EncounterView(entry)
        "trial" -> TrialsView(entry)
        "shop" -> ShopView(entry)
        "equipment" -> EquipmentView(entry)
        "itemsort" -> ItemSortView(entry)
        "spheregrid" -> SphereGridView(entry)
        "customise" -> CustomiseView(entry)
        "blitzball" -> BlitzballView(entry)
        "bullets" -> BulletedList(entry)
        "image" -> ImageView(entry = entry, modifier = modifier)
        "table" -> TableView(entry)
        else -> Text("Unknown Entry Type", fontWeight = FontWeight.Bold, color = Color.Red, textDecoration = TextDecoration.Underline)
    }
}