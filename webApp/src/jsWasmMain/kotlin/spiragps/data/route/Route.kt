package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val title: String = "",
    val introduction: Introduction = Introduction(),
    val keywords: ArrayList<String> = arrayListOf(),
    val chapters: ArrayList<Chapter> = arrayListOf(),

    val conditions: ArrayList<Condition> = arrayListOf()
)