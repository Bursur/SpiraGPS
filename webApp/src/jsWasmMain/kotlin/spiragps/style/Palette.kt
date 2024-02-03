package spiragps.style

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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

    val typography = mutableStateOf(small)
    val selectedFontSize = mutableStateOf(getTextSizePreference())
    val useDyslexicFont = mutableStateOf(getDyslexicModePreference() == 1)

    fun getUpdatedFont(): TextStyles = when(selectedFontSize.value) {
        0 -> if(useDyslexicFont.value) dyslexicSmall else small
        1 -> if(useDyslexicFont.value) dyslexicMedium else medium
        2 -> if(useDyslexicFont.value) dyslexicLarge else large
        else -> if(useDyslexicFont.value) dyslexicSmall else small
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
                data = loadResource("SpiraGPS/fonts/ReadexPro-Light.ttf"),
                weight = FontWeight.Normal
            )
        )

        typography.value = getUpdatedFont()
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

    fun addKeywords(newKeywords: ArrayList<String>) {
        newKeywords.forEach {
            if(!keywords.contains(it))
                keywords.add(it)
        }
    }
}

var SpiraGPSColours = mutableStateOf(if(getDarkModePreference() == 1) darkScheme else lightScheme)
var SpiraGPSDarkMode = mutableStateOf(getDarkModePreference() == 1)
const val SpiraGPSVersion = "0.3.0"

@Composable
fun SpiraGPSTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = SpiraGPSColours.value.background,
            onBackground = SpiraGPSColours.value.onBackground
        )
    ) {
        ProvideTextStyle(LocalTextStyle.current.copy(letterSpacing = 0.sp)) {
            content()
        }
    }
}

fun getKeywordColour(word: String): Color = when(word) {
    "Tidus" -> SpiraGPSColours.value.tidus
    "Yuna" -> SpiraGPSColours.value.yuna
    "Auron" -> SpiraGPSColours.value.auron
    "Wakka" -> SpiraGPSColours.value.wakka
    "Kimahri" -> SpiraGPSColours.value.kimahri
    "Rikku" -> SpiraGPSColours.value.rikku
    "Lulu" -> SpiraGPSColours.value.lulu
    "Seymour" -> SpiraGPSColours.value.seymour
    "Enemy" -> SpiraGPSColours.value.enemy
    "Valefor" -> SpiraGPSColours.value.valefor
    "Ifrit" -> SpiraGPSColours.value.ifrit
    "Ixion" -> SpiraGPSColours.value.ixion
    "Shiva" -> SpiraGPSColours.value.shiva
    "Bahamut" -> SpiraGPSColours.value.bahamut
    "BUG!" -> SpiraGPSColours.value.bug
    else -> SpiraGPSColours.value.text
}