package spiragps.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import spiragps.data.route.Chapter
import spiragps.data.route.ConditionState
import spiragps.data.route.ContentsState
import spiragps.data.route.Entry
import spiragps.data.route.Route
import spiragps.style.SpiraGPSColours

@Composable
fun RouteView(route: Route, conditionState: ConditionState, contentsState: ContentsState) {
    val scrollableState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val bgColour = animateColorAsState(SpiraGPSColours.background)

    val titlePositions: MutableMap<Int, Float> = mutableMapOf()
    var scrollOffset = 0f

    Box(
        modifier = Modifier
            .wrapContentSize()
            .onGloballyPositioned {
                scrollOffset = it.positionInRoot().y
            }
            .background(bgColour.value)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 10.dp)
                .verticalScroll(
                    state = scrollableState,
                    enabled = true
                )
        ) {
            // Title
            TitleView(title = route.title)

            // Intro
            IntroductionView(route.introduction, conditionState)

            // Chapters
            route.chapters.forEach { chapter: Chapter ->
                ChapterView(chapter = chapter, conditionState = conditionState) { name: Int, position: Float ->
                    titlePositions[name] = position
                }
            }
        }

        SmallFloatingActionButton(
            onClick = {
                scope.launch {
                    scrollableState.animateScrollTo(0)
                }
            },
            containerColor = SpiraGPSColours.fabBackgroundColour,
            contentColor = SpiraGPSColours.fabIconColour,
            modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = 60.dp, horizontal = 10.dp)
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, "")
        }
    }

    LaunchedEffect(contentsState.selectedChapter) {
        if(titlePositions.containsKey(contentsState.selectedChapter))
            scrollableState.animateScrollTo((titlePositions[contentsState.selectedChapter]!!.toInt() + scrollableState.value) - scrollOffset.toInt())
    }
}