package com.bursur.spiragps.route.data

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    var title: String = "",
    var entries: ArrayList<Entry> = arrayListOf(),
    var index: Int = -1
)