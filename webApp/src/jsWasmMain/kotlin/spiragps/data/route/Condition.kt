package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val name: String = "",
    val defaultState: Boolean = false
)