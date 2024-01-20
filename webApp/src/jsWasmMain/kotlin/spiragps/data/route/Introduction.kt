package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Introduction(
    val entries: ArrayList<Entry> = arrayListOf()
)