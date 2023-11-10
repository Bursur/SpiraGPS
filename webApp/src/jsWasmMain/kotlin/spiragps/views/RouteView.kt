package spiragps.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import spiragps.data.Chapter
import spiragps.data.ConditionState
import spiragps.data.ContentsState
import spiragps.data.Entry
import spiragps.data.Route
import spiragps.style.SpiraGPSColours

@Composable
fun RouteView(route: Route, conditionState: ConditionState, contentsState: ContentsState) {
    val scrollableState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.wrapContentSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(.65f)
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
            TitleView(title = route.title, contentsState = contentsState)

            // Intro
            IntroductionView(route.introduction)

            // Chapters
            route.chapters.forEach { chapter: Chapter ->
                ChapterView(chapter = chapter, conditionState = conditionState, contentsState = contentsState)
            }

            Divider(
                color = SpiraGPSColours.background,
                modifier = Modifier.padding(vertical = 50.dp)
            )

            BulletedList(
                entry = Entry(
                    text = "ToDo: (In no particular order)",
                    guide = arrayListOf(
                        "Add an ImageView, mostly for the spheregrids.",
                        "Spheregrids will likely be an array of entries, much like blitzball.",
                        "Add a TableView, this should have a columns value and be told if it should be vertically or horizontally aligned.",
                        "Add new keywords to the keywords array (Split from palette?)",
                        "Make a landing page with some info on it while you chose a route.",
                        "Externalise the notes properly",
                        "Version history dialog",
                        "Fix the spacing on the Customise View",
                        "Clean up Blitzball formatting",
                        "Route Editor?",
                    )
                )
            )
        }

        SmallFloatingActionButton(
            onClick = {
                scope.launch {
                    scrollableState.animateScrollTo(0)
                }
            },
            containerColor = SpiraGPSColours.fabBackgroundColour,
            contentColor = SpiraGPSColours.fabIconColour,
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp)
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, "")
        }
    }
}