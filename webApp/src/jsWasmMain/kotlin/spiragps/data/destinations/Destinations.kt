package spiragps.data.destinations

import kotlinx.serialization.Serializable

@Serializable
data class Destinations(
    var destinations: ArrayList<Destination> = arrayListOf()
)