package spiragps.data

import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    // Misc.
    val type: String = "",
    val entries: ArrayList<Entry> = arrayListOf(),
    val guide: ArrayList<String> = arrayListOf(),
    val image: String = "",
    val bold: Boolean = false,
    val trailingBreak: Boolean = false,
    val minimised: Boolean = false,
    val requirement: Requirement? = null,

    // Info
    val text: String = "",

    // Battle
    val enemy: String = "",
    val health: Int = 0,

    // Trial
    // Use image and guide

    // Shop
    val cost: Int = 0,
    val buy: ArrayList<String> = arrayListOf(),
    val sell: ArrayList<String> = arrayListOf(),

    // Equipment
    // Use guide

    // Item Sort
    // Use image(?) and guide

    // Sphere Grid
    // Use image and guide (Plural?)

    // Blitzball
    // Use guide (more formatting options?)

    // Customise
    val item: String = ""
)