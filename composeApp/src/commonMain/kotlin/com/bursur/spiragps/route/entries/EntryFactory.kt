package com.bursur.spiragps.route.entries

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.components.image.ImageView
import com.bursur.spiragps.components.info.InfoView
import com.bursur.spiragps.components.table.TableView
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.battle.BattleView
import com.bursur.spiragps.route.entries.blitzball.BlitzballView
import com.bursur.spiragps.route.entries.customise.CustomiseView
import com.bursur.spiragps.route.entries.encounter.EncounterView
import com.bursur.spiragps.route.entries.equipment.EquipmentView
import com.bursur.spiragps.route.entries.itemsort.ItemSortView
import com.bursur.spiragps.route.entries.shop.ShopView
import com.bursur.spiragps.route.entries.spheregrid.SphereGridView
import com.bursur.spiragps.route.entries.tip.TipView
import com.bursur.spiragps.route.entries.trials.TrialsView

@Composable
fun createEntry(entry: Entry) {
    return when(entry.type) {
        //Panels
        "battle" -> BattleView(entry)
        "encounter" -> EncounterView(entry)
        "trial" -> TrialsView(entry)
        "shop" -> ShopView(entry)
        "equipment" -> EquipmentView(entry)
        "itemsort" -> ItemSortView(entry)
        "spheregrid" -> SphereGridView(entry)
        "customise" -> CustomiseView(entry)
        "blitzball" -> BlitzballView(entry)
        "tip" -> TipView(entry)
        // Components
        "info" -> InfoView(entry)
        "bullets" -> BulletedList(entry)
        "image" -> ImageView(entry)
        "table" -> TableView(entry)
        else -> Text("Unknown Entry Type", fontWeight = FontWeight.Bold, color = Color.Red, textDecoration = TextDecoration.Underline)
    }
}