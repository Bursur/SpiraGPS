package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    var title: String = "",
    var entries: ArrayList<Entry> = arrayListOf(),
    var index: Int = -1
)