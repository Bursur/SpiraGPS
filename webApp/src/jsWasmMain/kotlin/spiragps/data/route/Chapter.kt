package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val title: String = "",
    val entries: ArrayList<Entry> = arrayListOf()
)