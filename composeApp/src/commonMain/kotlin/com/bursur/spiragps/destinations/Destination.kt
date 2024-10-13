package com.bursur.spiragps.destinations

import kotlinx.serialization.Serializable

@Serializable
data class Destination(
    val name: String = "",
    val data: String = "",
    val image: String = "",
    val author: String = "",
    val id: String
)