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
import spiragps.data.route.Chapter
import spiragps.data.route.ConditionState
import spiragps.data.route.ContentsState
import spiragps.data.route.Entry
import spiragps.data.route.Route
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

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
            Text(text = "This is VERY WIP route for Boosters%. Full credits to Mtbanger for these notes. The notes are Mtbangers notes.", fontFamily = SpiraGPSText.fontFamily)
            Text(text = "Credits from Mt's notes: Credit to psychonauter, MorphaSRDC, and ChrisTenarium for original Booster% notes and route, MrTyton for the Zanarkand Trials map, to CrimsonInferno and the FFX Blitzball Haters Club HQ Big Nerds for helping me make this bad idea almost good.", fontFamily = SpiraGPSText.fontFamily)

            // Title
            TitleView(title = route.title, contentsState = contentsState)

            // Intro
            IntroductionView(route.introduction)

            // Chapters
            route.chapters.forEach { chapter: Chapter ->
                ChapterView(chapter = chapter, conditionState = conditionState, contentsState = contentsState)
            }

            // TODO LIST REMOVE THIS!!
            Divider(
                color = SpiraGPSColours.background,
                modifier = Modifier.padding(vertical = 50.dp)
            )

            BulletedList(
                entry = Entry(
                    text = "ToDo: (In no particular order)",
                    guide = arrayListOf(
                        "Version history dialog",
                        "Route Editor?",
                        "Contents Scrolling to the top of the page",
                        "Load Local Route jsons?"
                    )
                )
            )

            Divider(
                color = SpiraGPSColours.background,
                modifier = Modifier.padding(vertical = 50.dp)
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