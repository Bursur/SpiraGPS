package spiragps.data

import kotlinx.serialization.Serializable

@Serializable
data class Requirement(
    val condition: String = "",
    val state: Boolean = false
)