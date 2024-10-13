package com.bursur.spiragps.route.data

import kotlinx.serialization.Serializable

@Serializable
data class Introduction(
    val entries: ArrayList<Entry> = arrayListOf()
)