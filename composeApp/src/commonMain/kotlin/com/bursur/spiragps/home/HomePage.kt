package com.bursur.spiragps.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.destinations.SpiraDestinations
import com.bursur.spiragps.home.components.EditorHelpButton
import com.bursur.spiragps.home.components.EditorSelectButton
import com.bursur.spiragps.home.components.LocalRouteSelectButton
import com.bursur.spiragps.home.components.RouteSelectButton
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.platform.isWebSite
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.title.SpiraGPSTitle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomePage(navigationState: NavigationState) {
    val textColour = animateColorAsState(SpiraGPSColours.text)

    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SpiraGPSTitle()F

        Text(
            text = "Select your destination",
            style = SpiraGPSText.typography.info,
            textAlign = TextAlign.Center,
            color = textColour.value,
            modifier = Modifier.padding(top = 10.dp)
        )

        val scrollState = rememberScrollState()
        FlowRow(
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            modifier = Modifier.padding(top = 25.dp)
                .verticalScroll(scrollState)
        ) {
            SpiraDestinations.list.destinations.forEach {
                RouteSelectButton(destination = it, navigationState = navigationState)
            }

            if(isWebSite()) {
                LocalRouteSelectButton(navigationState)
                EditorSelectButton(navigationState)
                EditorHelpButton(navigationState)
            }
        }
    }
}