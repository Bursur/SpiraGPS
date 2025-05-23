package com.bursur.spiragps.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.destinations.Destination
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.seiko.imageloader.rememberImagePainter

external fun setClipboard(url: String)

@Composable
fun RouteSelectButton(destination: Destination, navigationState: NavigationState) {
    val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)
    val textColour by animateColorAsState(SpiraGPSColours.text)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val uriHandler = LocalUriHandler.current

    Surface(
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        color = bgColour,
        modifier = Modifier.hoverable(interactionSource).padding(bottom = 10.dp)
    ) {
        Box(modifier = Modifier.width(200.dp)) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .clickable {
                        navigationState.selectedRouteUrl = destination.data
                        navigationState.currentPage = NavigationState.ROUTE
                    }
            ) {
                Image(
                    painter = rememberImagePainter(
                        if (destination.image.isEmpty())
                            "https://bursur.github.io/SpiraGPS/AnyPercent.jpg"
                        else
                            "https://bursur.github.io/${destination.image}"
                    ),
                    contentDescription = null
                )
            }

            Overlay(
                destination = destination,
                textColour = textColour,
                bgColour = bgColour,
                modifier = Modifier.align(Alignment.BottomCenter),
                isExpanded = isHovered
            )

            // Download button
            if(isHovered) {
                IconButton(
                    onClick = {
                        uriHandler.openUri("https://bursur.github.io/${destination.data}")
                    }
                ) {
                    Icon(imageVector = Icons.Default.Download, contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun Overlay(modifier: Modifier = Modifier, destination: Destination, textColour: Color, bgColour: Color, isExpanded: Boolean) {
    Column(
        modifier = modifier
            .background(bgColour)
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize()
            .padding(bottom = 5.dp, start = 5.dp, end = 5.dp)
    ) {
        Text(
            text = destination.name,
            style = SpiraGPSText.typography.info,
            textAlign = TextAlign.Center,
            color = textColour,
            modifier = Modifier.fillMaxWidth()
        )

        if(isExpanded) {
            Text(
                "Author: ${destination.author}",
                style = SpiraGPSText.typography.info,
                color = textColour
            )
        }
    }
}