package com.bursur.spiragps.route.entries

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.bursur.spiragps.components.bulletedlist.BulletEditorPanel
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.components.image.ImageEditorPanel
import com.bursur.spiragps.components.image.ImageView
import com.bursur.spiragps.components.info.InfoEditorPanel
import com.bursur.spiragps.components.info.InfoView
import com.bursur.spiragps.components.link.Link
import com.bursur.spiragps.components.link.LinkEditorPanel
import com.bursur.spiragps.components.table.TableEditorPanel
import com.bursur.spiragps.components.table.TableView
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.battle.BattleEditorPanel
import com.bursur.spiragps.route.entries.battle.BattleView
import com.bursur.spiragps.route.entries.blitzball.BlitzballEditorPanel
import com.bursur.spiragps.route.entries.blitzball.BlitzballView
import com.bursur.spiragps.route.entries.customise.CustomisationEditorPanel
import com.bursur.spiragps.route.entries.customise.CustomiseView
import com.bursur.spiragps.route.entries.encounter.EncounterEditorPanel
import com.bursur.spiragps.route.entries.encounter.EncounterView
import com.bursur.spiragps.route.entries.equipment.EquipmentEditorPanel
import com.bursur.spiragps.route.entries.equipment.EquipmentView
import com.bursur.spiragps.route.entries.itemsort.ItemSortEditorPanel
import com.bursur.spiragps.route.entries.itemsort.ItemSortView
import com.bursur.spiragps.route.entries.shop.ShopEditorPanel
import com.bursur.spiragps.route.entries.shop.ShopView
import com.bursur.spiragps.route.entries.spheregrid.SphereGridView
import com.bursur.spiragps.route.entries.spheregrid.SpheregridEditorPanel
import com.bursur.spiragps.route.entries.tip.TipEditorPanel
import com.bursur.spiragps.route.entries.tip.TipView
import com.bursur.spiragps.route.entries.trials.TrialEditorPanel
import com.bursur.spiragps.route.entries.trials.TrialsView

@Composable
fun createEntry(entry: Entry, editor: Boolean = false, selectedEntry: Entry = Entry(), conditions: ArrayList<Condition> = arrayListOf()) {
    return when(entry.type) {
        //Panels
        "battle" -> if(!editor) BattleView(entry) else BattleEditorPanel(entry, selectedEntry, conditions)
        "encounter" -> if(!editor) EncounterView(entry) else EncounterEditorPanel(entry, selectedEntry, conditions)
        "trial" -> if(!editor) TrialsView(entry) else TrialEditorPanel(entry, selectedEntry, conditions)
        "shop" -> if(!editor) ShopView(entry) else ShopEditorPanel(entry, selectedEntry)
        "equipment" -> if(!editor) EquipmentView(entry) else EquipmentEditorPanel(entry, selectedEntry)
        "itemsort" -> if(!editor) ItemSortView(entry) else ItemSortEditorPanel(entry, selectedEntry)
        "spheregrid" -> if(!editor) SphereGridView(entry) else SpheregridEditorPanel(entry, selectedEntry, conditions)
        "customise" -> if(!editor) CustomiseView(entry) else CustomisationEditorPanel(entry, selectedEntry)
        "blitzball" -> if(!editor) BlitzballView(entry) else BlitzballEditorPanel(entry, selectedEntry, conditions)
        "tip" -> if(!editor) TipView(entry) else TipEditorPanel(entry, selectedEntry, conditions)
        // Components
        "info" -> if(!editor) InfoView(entry) else InfoEditorPanel(entry, selectedEntry)
        "bullets" -> if(!editor) BulletedList(entry) else BulletEditorPanel(entry, selectedEntry)
        "image" -> if(!editor) ImageView(entry) else ImageEditorPanel(entry, selectedEntry)
        "table" -> if(!editor) TableView(entry) else TableEditorPanel(entry, selectedEntry)
        "link" -> if(!editor) Link(entry) else LinkEditorPanel(entry, selectedEntry)
        else -> Text("Unknown Entry Type", fontWeight = FontWeight.Bold, color = Color.Red, textDecoration = TextDecoration.Underline)
    }
}