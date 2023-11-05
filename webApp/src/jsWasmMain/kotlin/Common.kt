import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import spiragps.data.Chapter
import spiragps.data.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSTheme
import spiragps.views.BattleView
import spiragps.views.BlitzballView
import spiragps.views.EncounterView
import spiragps.views.EquipmentView
import spiragps.views.InfoView
import spiragps.views.ItemSortView
import spiragps.views.ShopView
import spiragps.views.SphereGridView
import spiragps.views.TitleView
import spiragps.views.TrialsView
import spiragps.data.placeholderRoute
import spiragps.data.Route
import spiragps.views.BulletedList
import spiragps.views.CustomiseView

@Composable
internal fun SpiraGPSWeb() {

    val data = Json.decodeFromString<Route>(placeholderRoute)

    SpiraGPSTheme {
        val scrollableState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp)
                .verticalScroll(
                    state = scrollableState,
                    enabled = true
                )
        ) {
            Text(text = "This is VERY WIP route for Boosters%. Full credits to Mtbanger for these notes. The notes are Mtbangers notes.")
            Text(text = "Credits from Mt's notes: Credit to psychonauter, MorphaSRDC, and ChrisTenarium for original Booster% notes and route, MrTyton for the Zanarkand Trials map, to CrimsonInferno and the FFX Blitzball Haters Club HQ Big Nerds for helping me make this bad idea almost good.")
            TitleView(data.title)
            Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))

            data.chapters.forEach { chapter: Chapter ->
                TitleView(chapter.title)
                Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))

                chapter.entries.forEach { entry: Entry ->
                    when(entry.type) {
                        "info" -> InfoView(entry.text, entry.bold)
                        "battle" -> BattleView(entry.enemy, entry.health, entry.guide)
                        "encounter" -> EncounterView(entry.guide)
                        "trial" -> TrialsView(entry.guide)
                        "shop" -> ShopView(entry.cost, entry.buy, entry.sell)
                        "equipment" -> EquipmentView(entry.guide)
                        "itemsort" -> ItemSortView(entry.guide)
                        "spheregrid" -> SphereGridView(entry.guide)
                        "customise" -> CustomiseView(entry.item, entry.guide)
                        "blitzball" -> BlitzballView(entry.entries)
                        "bullets" -> BulletedList(title = entry.text, points = entry.guide)
                    }

                    if(entry.trailingBreak)
                        Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}
