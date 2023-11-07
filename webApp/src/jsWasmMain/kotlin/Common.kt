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
import spiragps.views.TitleView
import spiragps.data.placeholderRoute
import spiragps.data.Route
import spiragps.views.BulletedList
import spiragps.views.createEntry

@Composable
internal fun SpiraGPS() {

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
            Text(text = "This is VERY WIP route for Boosters%. Full credits to Mtbanger for these notes. The notes are Mtbangers notes.", modifier = Modifier.fillMaxWidth(.5f))
            Text(text = "Credits from Mt's notes: Credit to psychonauter, MorphaSRDC, and ChrisTenarium for original Booster% notes and route, MrTyton for the Zanarkand Trials map, to CrimsonInferno and the FFX Blitzball Haters Club HQ Big Nerds for helping me make this bad idea almost good.", modifier = Modifier.fillMaxWidth(.5f))
            TitleView(data.title)

            BulletedList(
                entry = Entry(
                    text = "ToDo: (In no particular order)",
                    guide = arrayListOf(
                        "Make a RouteView that contains IntroView and ChapterViews which contains Entries.",
                        "Make Chapters shrinkable if you click on their title?",
                        "Make it so that Entries can be minimised by default via the JSON data set.",
                        "Add an ImageView, mostly for the spheregrids.",
                        "Spheregrids will likely be an array of entries, much like blitzball.",
                        "Add a TableView, this should have a columns value and be told if it should be vertically or horizontally aligned.",
                        "Make the bullets so that the title can be bolded with the bold property.",
                        "Add new keywords to the keywords array (Split from palette?)",
                        "Make a landing page with some info on it while you chose a route.",
                        "Make an anchored header that allows you to change the route on the fly.",
                        "Add in Requirements for entries.",
                        "Look at setting it all up with a view model thingumy dingle.",
                        "Finish the example Boosters% notes",
                        "Externalise the notes properly"
                    )
                )
            )

            Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))

            data.chapters.forEach { chapter: Chapter ->
                TitleView(chapter.title)
                Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))

                chapter.entries.forEach { entry: Entry ->
                    createEntry(entry)

                    if(entry.trailingBreak)
                        Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}
