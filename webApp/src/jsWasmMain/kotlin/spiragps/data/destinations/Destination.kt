package spiragps.data.destinations

import kotlinx.serialization.Serializable

@Serializable
data class Destination(
    val name: String = "",
    val data: String = "",
    val image: String = "",
    val author: String = "",
    val description: String = ""
)