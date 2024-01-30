package spiragps.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp
import spiragps.utils.loadResource

object SpiraGPSText {
    var fontFamily: FontFamily? = null
    lateinit var typography: TextStyles

    const val BULLET_CHAR = "\u2022"

    suspend fun loadFonts() {
        fontFamily = FontFamily(
            Font(
                identity = "Kanit",
                data = loadResource("SpiraGPS/fonts/Kanit-Light.ttf"),
                weight = FontWeight.Normal
            )
        )

        typography = small
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

var SpiraGPSColours = lightScheme

@Composable
fun SpiraGPSTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    SpiraGPSColours = if (isDarkMode) darkScheme else lightScheme
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
    else -> SpiraGPSColours.black
}