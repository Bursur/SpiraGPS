package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
            .padding(top = 30.dp)
            .verticalScroll(
                state = scrollableState,
                enabled = true
            )
    ) {
        // Title
        TitleView(route.title)

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
                    "Make Chapters collapsable if you click on their title.",
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
    }
}