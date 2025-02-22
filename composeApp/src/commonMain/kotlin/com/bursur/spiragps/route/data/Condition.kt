package com.bursur.spiragps.route.data

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    var name: String = "not set",
    var options: ArrayList<String> = arrayListOf()
)