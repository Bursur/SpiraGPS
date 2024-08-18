package spiragps.style

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp
import spiragps.utils.loadResource

external fun getDarkModePreference(): Int
external fun getTextSizePreference(): Int
external fun getDyslexicModePreference(): Int

object SpiraGPSText {
    var fontFamily: FontFamily? = null
    var dyslexicFont: FontFamily? = null

    var typography by mutableStateOf(small)
    var selectedFontSize by mutableStateOf(getTextSizePreference())
    var useDyslexicFont by mutableStateOf(getDyslexicModePreference() == 1)

    fun getUpdatedFont(): TextStyles = when(selectedFontSize) {
        0 -> if(useDyslexicFont) dyslexicSmall else small
        1 -> if(useDyslexicFont) dyslexicMedium else medium
        2 -> if(useDyslexicFont) dyslexicLarge else large
        else -> if(useDyslexicFont) dyslexicSmall else small
    }

    const val BULLET_CHAR = "\u2022"

    suspend fun loadFonts() {
        fontFamily = FontFamily(
            Font(
                identity = "Kanit",
                data = loadResource("SpiraGPS/fonts/Kanit-Light.ttf"),
                weight = FontWeight.Normal
            )
        )

        dyslexicFont = FontFamily(
            Font(
                identity = "ReadexPro",
                data = loadResource("SpiraGPS/fonts/Lexend-ExtraLight.ttf"),
                weight = FontWeight.Normal
            )
        )

        typography = getUpdatedFont()
    }

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

        // Internal
        "BUG!"
    )

    val specialCharacters = arrayListOf(
        "↓",
        "←",
        "→",
        "↑"
    )

    fun addKeywords(newKeywords: ArrayList<String>) {
        newKeywords.forEach {
            if(!keywords.contains(it))
                keywords.add(it)
        }
    }
}

var SpiraGPSColours by mutableStateOf(if(getDarkModePreference() == 1) darkScheme else lightScheme)
var SpiraGPSDarkMode by mutableStateOf(getDarkModePreference() == 1)
const val SpiraGPSVersion = "0.8.5 - alpha"

@Composable
fun SpiraGPSTheme(content: @Composable () -> Unit) {
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
    "BUG!" -> SpiraGPSColours.bug
    else -> SpiraGPSColours.text
}