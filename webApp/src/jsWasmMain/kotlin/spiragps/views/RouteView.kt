package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.Chapter
import spiragps.data.Entry
import spiragps.data.Route

@Composable
fun RouteView(route: Route) {
    val scrollableState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(.5f)
            .wrapContentHeight()
            .padding(top = 10.dp)
            .verticalScroll(
                state = scrollableState,
                enabled = true
            )
    ) {
        // Disclaimers (Move this later)
        Text(text = "This is VERY WIP route for Boosters%. Full credits to Mtbanger for these notes. The notes are Mtbangers notes.")
        Text(text = "Credits from Mt's notes: Credit to psychonauter, MorphaSRDC, and ChrisTenarium for original Booster% notes and route, MrTyton for the Zanarkand Trials map, to CrimsonInferno and the FFX Blitzball Haters Club HQ Big Nerds for helping me make this bad idea almost good.")

        // Title
        TitleView(title = route.title)

        // Intro
        IntroductionView(route.introduction)

        // Chapters
        route.chapters.forEach { chapter: Chapter ->
            ChapterView(chapter)
        }

        BulletedList(
            entry = Entry(
                text = "ToDo: (In no particular order)",
                guide = arrayListOf(
                    "Add an ImageView, mostly for the spheregrids.",
                    "Spheregrids will likely be an array of entries, much like blitzball.",
                    "Add a TableView, this should have a columns value and be told if it should be vertically or horizontally aligned.",
                    "Add new keywords to the keywords array (Split from palette?)",
                    "Make a landing page with some info on it while you chose a route.",
                    "Make an anchored header that allows you to change the route on the fly.",
                    "Add in Requirements for entries.",
                    "Look at setting it all up with a view model thingumy dingle.",
                    "Finish the example Boosters% notes",
                    "Externalise the notes properly",
                    "Contents page/section",
                    "Version history dialog"
                )
            )
        )
    }
}