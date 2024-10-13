package com.bursur.spiragps.deeplinks

import com.bursur.spiragps.destinations.SpiraDestinations
import com.bursur.spiragps.navigation.NavigationState

external fun getUrl(): String

private var params: Map<String, List<String>> = emptyMap()

actual fun followRouteLink(navigationState: NavigationState) {
    params.ifEmpty { params = getQueryString() }

    if(params.containsKey("route_id")) {
        SpiraDestinations.list.destinations.find {
            it.id == params["route_id"]!![0]
        }?.let {
            navigationState.selectedRouteUrl = it.data
            navigationState.currentPage = NavigationState.ROUTE
        }
    }
}

actual fun followEditorLink(navigationState: NavigationState) {
    params.ifEmpty { params = getQueryString() }

    if(params.containsKey("editor") && params["editor"]!![0] == "true")
        navigationState.currentPage = NavigationState.EDITOR
}

private fun getQueryString() : Map<String, List<String>> {
    val url = getUrl()
    return if(!url.contains("?"))
        emptyMap()
    else
        url.substring(url.indexOf("?") + 1)
            .split("&")
            .mapNotNull { keyAndValue ->
                val keyAndValueList = keyAndValue.split("=")
                keyAndValueList.takeIf { it.size == 2 }
            }
            .mapNotNull { keyAndValueList ->
                val (key, value) = keyAndValueList
                (key to value.trim()).takeIf { value.isNotBlank() } // Don't take empty params
            }
            .groupBy { (key, _) -> key }
            .map { mapEntry ->
                mapEntry.key to mapEntry.value.map { it.second }
            }
            .toMap()
}