package com.bursur.spiragps.components.backbutton

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.highlightKeywords

@Composable
fun BackButton(modifier: Modifier = Modifier, navigationState: NavigationState) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    Text(
        text = highlightKeywords("← Back"),
        style = SpiraGPSText.typography.backButton,
        color = textColour,
        modifier = modifier
            .padding(bottom = 20.dp)
            .clickable { navigationState.currentPage = NavigationState.HOME }
            .wrapContentSize()
    )
}