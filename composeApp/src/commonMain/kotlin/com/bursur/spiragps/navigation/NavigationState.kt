package com.bursur.spiragps.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
interface NavigationState {
    companion object {
        const val HOME = "Home"
        const val ROUTE = "Route"
        const val EDITOR = "Editor"
    }

    var currentPage: String
    var selectedRouteUrl: String
    var data: String
}

private class NavigationStateImpl(currentPage: String, selectedRouteUrl: String, data: String = "") : NavigationState {
    override var currentPage: String by mutableStateOf(currentPage)
    override var selectedRouteUrl: String by mutableStateOf(selectedRouteUrl)
    override var data: String by mutableStateOf(data)

    companion object {
        val saver = Saver<NavigationStateImpl, List<Any>>(
            save = {
                listOf(
                    it.currentPage,
                    it.selectedRouteUrl,
                    it.data
                )
            },
            restore = {
                NavigationStateImpl(it[0] as String, it[1] as String, it[2] as String)
            }
        )
    }
}

@Composable
fun rememberNavigationState(): NavigationState = rememberSaveable(
    saver = NavigationStateImpl.saver
) {
    NavigationStateImpl(NavigationState.HOME, "", "")
}