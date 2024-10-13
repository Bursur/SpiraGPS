package com.bursur.spiragps.editor

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.bursur.spiragps.components.bulletedlist.BulletEditorPanel
import com.bursur.spiragps.components.image.ImageEditorPanel
import com.bursur.spiragps.components.info.InfoEditorPanel
import com.bursur.spiragps.components.table.TableEditorPanel
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.battle.BattleEditorPanel
import com.bursur.spiragps.route.entries.blitzball.BlitzballEditorPanel
import com.bursur.spiragps.route.entries.customise.CustomisationEditorPanel
import com.bursur.spiragps.route.entries.encounter.EncounterEditorPanel
import com.bursur.spiragps.route.entries.equipment.EquipmentEditorPanel
import com.bursur.spiragps.route.entries.itemsort.ItemSortEditorPanel
import com.bursur.spiragps.route.entries.shop.ShopEditorPanel
import com.bursur.spiragps.route.entries.spheregrid.SpheregridEditorPanel
import com.bursur.spiragps.route.entries.tip.TipEditorPanel
import com.bursur.spiragps.route.entries.trials.TrialEditorPanel
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun createEditorPanel(entry: Entry, conditions: ArrayList<Condition>) {
    return when(entry.type) {
        // Entries
        "info" -> InfoEditorPanel(entry = entry)
        "image" -> ImageEditorPanel(entry = entry)
        "bullets" -> BulletEditorPanel(entry = entry)
        "table" -> TableEditorPanel(entry = entry)
        // Panels
        "battle" -> BattleEditorPanel(entry = entry)
        "encounter" -> EncounterEditorPanel(entry = entry)
        "trial" -> TrialEditorPanel(entry = entry, conditions = conditions)
        "shop" -> ShopEditorPanel(entry = entry)
        "equipment" -> EquipmentEditorPanel(entry = entry)
        "itemsort" -> ItemSortEditorPanel(entry = entry)
        "spheregrid" -> SpheregridEditorPanel(entry = entry, conditions = conditions)
        "customise" -> CustomisationEditorPanel(entry = entry)
        "blitzball" -> BlitzballEditorPanel(entry = entry, conditions = conditions)
        "tip" -> TipEditorPanel(entry = entry, conditions = conditions)

        else -> Text("Select an Entry type", fontFamily = SpiraGPSText.fontFamily, fontWeight = FontWeight.Bold)
    }
}