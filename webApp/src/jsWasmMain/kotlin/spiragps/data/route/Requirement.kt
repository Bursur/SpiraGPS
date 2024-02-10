package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Requirement(
    var condition: String = "",
    var state: Boolean = false
)