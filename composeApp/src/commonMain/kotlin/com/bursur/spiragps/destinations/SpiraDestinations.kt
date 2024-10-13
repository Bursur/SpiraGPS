package com.bursur.spiragps.destinations

import com.bursur.spiragps.utils.loadJsonFile
import kotlinx.serialization.json.Json

object SpiraDestinations {
    lateinit var list: Destinations

    suspend fun loadDestinations() {
        val jsonString = loadJsonFile("SpiraGPS/routes.json")
        list = Json.decodeFromString(jsonString)
    }
}