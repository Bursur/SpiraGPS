package com.bursur.spiragps.route

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.platform.isApp
import com.bursur.spiragps.route.chapter.ChapterView
import com.bursur.spiragps.route.conditions.ConditionState
import com.bursur.spiragps.route.contents.ContentsState
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.route.introduction.IntroductionView
import com.bursur.spiragps.route.title.TitleView
import com.bursur.spiragps.spherecounter.SphereCounter
import com.bursur.spiragps.theme.SpiraGPSColours
import dev.wonddak.capturable.capturable
import dev.wonddak.capturable.controller.rememberCaptureController
import io.github.alexzhirkevich.qrose.toByteArray
import io.github.vinceglb.filekit.core.FileKit
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RouteView(route: Route, conditionState: ConditionState, contentsState: ContentsState) {
    val scrollableState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val bgColour by animateColorAsState(SpiraGPSColours.background)

    val titlePositions: MutableMap<Int, Float> = mutableMapOf()
    var scrollOffset = 0f

    var footerHeight by remember { mutableStateOf(1) }

    val captureController = rememberCaptureController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                scrollOffset = it.positionInRoot().y
            }
            .background(bgColour)
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
                .capturable(captureController)
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

            Spacer(Modifier.height(footerHeight.dp))
        }

        Row(
            modifier = Modifier.align(Alignment.BottomEnd).padding(vertical = 60.dp, horizontal = 10.dp)
        ) {
            SmallFloatingActionButton(
                onClick = {
                    scope.launch {
                        val bmpCapture = captureController.captureAsync()
                        try {
                            val bitmap = bmpCapture.await()
                            val data = bitmap.toByteArray()
                            if (data.isNotEmpty()) {
                                FileKit.saveFile(
                                    baseName = route.title.ifEmpty { route.title },
                                    extension = "png",
                                    initialDirectory = "",
                                    bytes = data
                                )
                            }
                        } catch (e: Throwable) {

                        }
                    }
                },
                containerColor = SpiraGPSColours.fabBackgroundColour,
                contentColor = SpiraGPSColours.fabIconColour
            ) {
                Icon(Icons.Filled.Download, "")
            }

            SmallFloatingActionButton(
                onClick = {
                    scope.launch {
                        scrollableState.animateScrollTo(0)
                    }
                },
                containerColor = SpiraGPSColours.fabBackgroundColour,
                contentColor = SpiraGPSColours.fabIconColour,
            ) {
                Icon(Icons.Filled.KeyboardArrowUp, "")
            }

            if(isApp()) {
                SmallFloatingActionButton(
                    onClick = {
                        conditionState.sheetOpen = true
                    },
                    containerColor = SpiraGPSColours.fabBackgroundColour,
                    contentColor = SpiraGPSColours.fabIconColour,
                ) {
                    Icon(Icons.AutoMirrored.Filled.List, "")
                }
            }
        }

        SphereCounter(
            route = route,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .onSizeChanged {
                    footerHeight = it.height
                }
        )
    }

    LaunchedEffect(contentsState.selectedChapter) {
        if(titlePositions.containsKey(contentsState.selectedChapter))
            scrollableState.animateScrollTo((titlePositions[contentsState.selectedChapter]!!.toInt() + scrollableState.value) - scrollOffset.toInt())
    }
}