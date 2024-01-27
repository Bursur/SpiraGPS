package spiragps.data.route

import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    // Misc.
    var type: String = "",
    var entries: ArrayList<Entry> = arrayListOf(),
    var guide: ArrayList<String> = arrayListOf(),
    var image: String = "",
    var bold: Boolean = false,
    var trailingBreak: Boolean = true,
    var minimised: Boolean = false,
    var requirement: Requirement = Requirement(),
    var columns: Int = 1,

    // Info
    var text: String = "",

    // Battle
    var enemy: String = "",
    var health: Int = 0,

    // Trial
    // Use image and guide

    // Shop
    var cost: Int = 0,
    var buy: ArrayList<String> = arrayListOf(),
    var sell: ArrayList<String> = arrayListOf(),

    // Equipment
    // Use guide

    // Item Sort
    // Use image(?) and guide

    // Sphere Grid
    // Use image and guide (Plural?)

    // Blitzball
    // Use guide (more formatting options?)

    // Customise
    var item: String = ""
)