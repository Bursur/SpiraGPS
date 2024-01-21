package spiragps.data.destinations

import kotlinx.serialization.json.Json
import spiragps.utils.loadResource

object SpiraDestinations {
    lateinit var list: Destinations

    suspend fun loadDestinations() {
        val jsonString = loadResource("SpiraGPS/routes.json").decodeToString()
        list = Json.decodeFromString(jsonString)
    }
}