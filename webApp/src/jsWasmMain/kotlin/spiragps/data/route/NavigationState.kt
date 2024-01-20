package spiragps.data.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Stable
interface NavigationState {
    companion object {
        const val LANDING = "Landing"
        const val ROUTE = "Route"
        const val EDITOR = "Editor"
    }

    var currentPage: String
    var selectedRouteUrl: String
}

private class NavigationStateImpl(currentPage: String, selectedRouteUrl: String) : NavigationState {
    override var currentPage: String by mutableStateOf(currentPage)
    override var selectedRouteUrl: String by mutableStateOf(selectedRouteUrl)

    companion object {
        val saver = Saver<NavigationStateImpl, List<Any>>(
            save = {
                listOf(
                    it.currentPage,
                    it.selectedRouteUrl
                )
            },
            restore = {
                NavigationStateImpl(it[0] as String, it[1] as String)
            }
        )
    }
}

@Composable
fun rememberNavigationState(): NavigationState = rememberSaveable(
    saver = NavigationStateImpl.saver
) {
    NavigationStateImpl(NavigationState.LANDING, "")
}