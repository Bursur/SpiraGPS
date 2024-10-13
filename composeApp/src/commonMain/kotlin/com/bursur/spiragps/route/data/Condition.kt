package com.bursur.spiragps.route.data

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    var name: String = "",
    var defaultState: Boolean = false
)