package com.bursur.spiragps.route.data

import kotlinx.serialization.Serializable

@Serializable
data class Requirement(
    var condition: String = "",
    var state: String = "false"
)