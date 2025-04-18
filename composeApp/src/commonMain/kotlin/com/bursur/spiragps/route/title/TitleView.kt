package com.bursur.spiragps.route.title

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.platform.isWebSite
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun TitleView(modifier: Modifier = Modifier, title: String, isChapter: Boolean = false, positionCallback: (Float) -> Unit = { _: Float -> }) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    Column(modifier = Modifier.fillMaxWidth(if(isChapter) 1f else { if(isWebSite()) .75f else .95f })) {
        Text(
            text = title,
            textAlign = TextAlign.Right,
            style = SpiraGPSText.typography.routeTitle,
            color = textColour,
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    positionCallback(it.positionInRoot().y)
                }
        )
        HorizontalDivider(color = textColour, thickness = 2.dp)
    }
}