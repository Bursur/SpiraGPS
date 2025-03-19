package com.bursur.spiragps.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.utils.FileService
import kotlinx.coroutines.launch

@Composable
fun LocalRouteSelectButton(navigationState: NavigationState) {
    val scope = rememberCoroutineScope()
    var awaitingData by remember { mutableStateOf(false) }
    val bgColour = animateColorAsState(SpiraGPSColours.infoBackground)
    val textColour = animateColorAsState(SpiraGPSColours.text)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Surface(
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        color = bgColour.value,
        modifier = Modifier.hoverable(interactionSource).padding(bottom = 10.dp)
    ) {
        Box(modifier = Modifier.width(200.dp)) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        scope.launch {
                            awaitingData = true
                        }
                    }
            ) {
                AsyncImage(
                    model = "https://bursur.github.io/SpiraGPS/placeholder-map.jpg",
                    contentDescription = ""
                )
            }

            Overlay(
                textColour = textColour.value,
                bgColour = bgColour.value,
                modifier = Modifier.align(Alignment.BottomCenter),
                isExpanded = isHovered
            )
        }
    }

    LaunchedEffect(awaitingData) {
        if(awaitingData) {
            val data = FileService.loadFile()

            awaitingData = false

            if(data != "cancelled") {
                navigationState.data = data
                navigationState.currentPage = NavigationState.ROUTE
            }
        }
    }
}

@Composable
private fun Overlay(modifier: Modifier = Modifier, textColour: Color, bgColour: Color, isExpanded: Boolean) {
    Column(
        modifier = modifier
            .background(bgColour)
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .padding(bottom = 5.dp, start = 5.dp, end = 5.dp)
    ) {
        Text(
            text = "Load Route File",
            style = SpiraGPSText.typography.info,
            textAlign = TextAlign.Center,
            color = textColour,
            modifier = Modifier.fillMaxWidth()
        )

        if(isExpanded) {
            Text(
                "Load a locally stored route file.",
                style = SpiraGPSText.typography.info,
                color = textColour
            )
        }
    }
}