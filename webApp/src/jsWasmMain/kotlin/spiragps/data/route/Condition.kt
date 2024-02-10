package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    var name: String = "",
    var defaultState: Boolean = false
)