package com.bursur.spiragps.route.data

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    var title: String = "",
    var introduction: Introduction = Introduction(),
    var keywords: ArrayList<String> = arrayListOf(),
    var chapters: ArrayList<Chapter> = arrayListOf(),

    var conditions: ArrayList<Condition> = arrayListOf(),

    var speedSpheres: Int = 0,
    var powerSpheres: Int = 0
)