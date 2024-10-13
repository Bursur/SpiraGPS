package com.bursur.spiragps.route

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.contents.ContentsView
import com.bursur.spiragps.loading.LoadingView
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.platform.isApp
import com.bursur.spiragps.platform.isWebSite
import com.bursur.spiragps.route.conditions.ConditionalSheet
import com.bursur.spiragps.route.conditions.ConditionalView
import com.bursur.spiragps.route.conditions.rememberConditionState
import com.bursur.spiragps.route.contents.rememberContentsState
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.route.header.HeaderView
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.utils.loadJsonFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun RoutePage(navigationState: NavigationState) {
    var loading by remember { mutableStateOf(true) }
    var data: Route by remember { mutableStateOf(Route()) }

    if(!loading) {
        val contentsState = rememberContentsState()
        ModalNavigationDrawer(
            drawerContent = {
                if(isApp()) {
                    val bgColour by animateColorAsState(SpiraGPSColours.background)
                    Surface(
                        shadowElevation = 5.dp,
                        color = bgColour,
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp)
                    ) {
                        ContentsView(
                            chapters = data.chapters,
                            contentsState = contentsState,
                            navigationState = navigationState
                        )
                    }
                }
            },
            drawerState = rememberDrawerState(DrawerValue.Closed) { isApp() },
        ) {
            val conditionState = rememberConditionState(data.conditions)

            Row {
                if(isWebSite())
                    ContentsView(
                        chapters = data.chapters,
                        contentsState = contentsState,
                        navigationState = navigationState
                    )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header
                    if (isWebSite())
                        HeaderView(data.conditions, conditionState)

                    // Contents/Route
                    RouteView(data, conditionState, contentsState)
                }

                if (isApp())
                    ConditionalSheet(data.conditions, conditionState)
            }
        }
    }
    else
        LoadingView("Calculating Route...")

    //TODO: Look into cache busting!
    LaunchedEffect(Unit) {
        launch(Dispatchers.Default) {
            if (navigationState.selectedRouteUrl.isNotEmpty()) {
                val jsonString = loadJsonFile(navigationState.selectedRouteUrl)
                Json.decodeFromString<Route>(jsonString).let {
                    SpiraGPSText.addKeywords(it.keywords)
                    data = it
                }
            } else {
                Json.decodeFromString<Route>(navigationState.data).let {
                    SpiraGPSText.addKeywords(it.keywords)
                    data = it
                }
            }

            navigationState.data = ""
            navigationState.selectedRouteUrl = ""

            loading = false
        }
    }
}