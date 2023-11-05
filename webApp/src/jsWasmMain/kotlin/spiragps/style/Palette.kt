package spiragps.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object SpiraGPSColours {
    val background = Color(0xFFFFFFFF)
    val onBackground = Color(0xFF19191C)

    val text = Color(0xFF000000)
    val infoBackground = Color(0xFFF2F2F2)

    val battleBorder = Color(0xFFFF8080)
    val encounterBorder = Color(0xFFF5ACCD)
    val trialsBorder = Color(0xFFDE9A73)
    val sphereGridBorder = Color(0xFFDF809F)
    val equipmentBorder = Color(0xFFC7C8CA)
    val blitzballBorder = Color(0xFFD48C82)
    val itemSortBorder = Color(0xFF77DD77)
    val shopBorder = Color(0xFF8080FF)
    val customiseBorder = Color(0xFFAEC6CF)

    // Items Sort
    val itemSortBackground: ArrayList<Color> = arrayListOf(Color(0xFFDDDDDD), infoBackground)
    val itemSortWeights: ArrayList<FontWeight> = arrayListOf(FontWeight.Light, FontWeight.Bold)

    // Characters
    val tidus = Color(0xFF0000FE)
    val yuna = Color(0xFF80818D)
    val kimahri = Color(0xFF00AFB3)
    val auron = Color(0xFFF91D02)
    val wakka = Color(0xFFF6911E)
    val rikku = Color(0xFF009A57)
    val lulu = Color(0xFFBF056D)
    val seymour = Color(0xFFA1CD6F)
    val enemy = Color(0xFFED058E)

    // Aeons
    val valefor = Color(0xFFF39188)
    val ifrit = Color(0xFFEE1580)
    val ixion = Color(0xFFF39DC4)
    val shiva = Color(0xFF1CAEEF)
    val bahamut = Color(0xFF6241A8)

    // Misc.
    val black = Color(0xFF000000)

    val keywords = arrayListOf(
        // Characters
        "Tidus",
        "Yuna",
        "Auron",
        "Wakka",
        "Kimahri",
        "Rikku",
        "Lulu",
        "Seymour",
        "Enemy",
        "Any",
        "Jassu",
        "Keepa",
        "Balgerda",
        "Abus",

        // Aeons
        "Valefor",
        "Ifrit",
        "Ixion",
        "Shiva",
        "Bahamut",
        "Anima",

        // Misc.
        "Overdrive",
        "CS",
        "SD",
        "Touch the Save Sphere",
        "Skippable",
        "FMV",
        "Start",
        "Never Use Max Gil"
    )
}

@Composable
fun SpiraGPSTheme(content: @Composable () -> Unit) {
    isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = SpiraGPSColours.background,
            onBackground = SpiraGPSColours.onBackground
        )
    ) {
        ProvideTextStyle(LocalTextStyle.current.copy(letterSpacing = 0.sp)) {
            content()
        }
    }
}

fun getKeywordColour(word: String): Color = when(word) {
    "Tidus" -> SpiraGPSColours.tidus
    "Yuna" -> SpiraGPSColours.yuna
    "Auron" -> SpiraGPSColours.auron
    "Wakka" -> SpiraGPSColours.wakka
    "Kimahri" -> SpiraGPSColours.kimahri
    "Rikku" -> SpiraGPSColours.rikku
    "Lulu" -> SpiraGPSColours.lulu
    "Seymour" -> SpiraGPSColours.seymour
    "Enemy" -> SpiraGPSColours.enemy
    "Valefor" -> SpiraGPSColours.valefor
    "Ifrit" -> SpiraGPSColours.ifrit
    "Ixion" -> SpiraGPSColours.ixion
    "Shiva" -> SpiraGPSColours.shiva
    "Bahamut" -> SpiraGPSColours.bahamut
    else -> SpiraGPSColours.black
}