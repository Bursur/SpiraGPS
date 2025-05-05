package com.bursur.spiragps.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bursur.spiragps.platform.isWebSite
import com.bursur.spiragps.preferences.getDyslexicModePreference
import com.bursur.spiragps.preferences.getTextSizePreference
import com.bursur.spiragps.theme.fonts.loadFont

data class TextStyles(
    val info: TextStyle,
    val infoBold: TextStyle,

    val bulletTitle: TextStyle,
    val bulletTitleBold: TextStyle,
    val bulletPoint: TextStyle,

    val routeTitle: TextStyle,
    val chapterTitle: TextStyle,

    val landingTitle: TextStyle,

    val backButton: TextStyle,

    val contentsTitle: TextStyle,
    val contentsEntry: TextStyle,

    val conditionLabel: TextStyle
)

val small: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 24.sp else 19.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 24.sp else 19.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 50.sp else 45.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 20.sp else 15.sp,
            fontFamily = SpiraGPSText.fontFamily
        ),

        contentsTitle = TextStyle(
            fontSize = if(isWebSite()) 20.sp else 15.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = if(isWebSite()) 15.sp else 10.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 17.sp else 12.sp,
        )
    )

val medium: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 29.sp else 24.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 29.sp else 24.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 55.sp else 50.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 25.sp else 20.sp,
            fontFamily = SpiraGPSText.fontFamily
        ),

        contentsTitle = TextStyle(
            fontSize = if(isWebSite()) 25.sp else 20.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = if(isWebSite()) 20.sp else 15.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        )
    )

val large: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 34.sp else 29.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 44.sp else 39.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 60.sp else 55.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 30.sp else 25.sp,
            fontFamily = SpiraGPSText.fontFamily
        ),

        contentsTitle = TextStyle(
            fontSize = if(isWebSite()) 30.sp else 25.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = if(isWebSite()) 25.sp else 20.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        )
    )

val dyslexicSmall: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 24.sp else 19.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 24.sp else 19.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 50.sp else 45.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 20.sp else 15.sp,
            fontFamily = SpiraGPSText.dyslexicFont
        ),

        contentsTitle = TextStyle(
            fontSize = if(isWebSite()) 20.sp else 15.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = if(isWebSite()) 15.sp else 10.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 17.sp else 12.sp
        )
    )

val dyslexicMedium: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 29.sp else 24.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 29.sp else 24.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 55.sp else 50.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 25.sp else 20.sp,
            fontFamily = SpiraGPSText.dyslexicFont
        ),

        contentsTitle = TextStyle(
            fontSize = if(isWebSite()) 25.sp else 20.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = if(isWebSite()) 20.sp else 15.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 22.sp else 17.sp
        )
    )

val dyslexicLarge: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 34.sp else 29.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 44.sp else 39.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = if(isWebSite()) 60.sp else 55.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 30.sp else 25.sp,
            fontFamily = SpiraGPSText.dyslexicFont
        ),

        contentsTitle = TextStyle(
            fontSize = if(isWebSite()) 30.sp else 25.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = if(isWebSite()) 25.sp else 20.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = if(isWebSite()) 27.sp else 22.sp
        )
    )

object SpiraGPSText {
    var fontFamily: FontFamily? = null
    var dyslexicFont: FontFamily? = null
    var specialFont: FontFamily? = null

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
        fontFamily = loadFont("SpiraGPS/fonts/Kanit-Light.ttf", "Kanit", FontWeight.Normal)

        dyslexicFont = loadFont("SpiraGPS/fonts/Lexend-ExtraLight.ttf", "ReadexPro", FontWeight.Normal)

        specialFont = loadFont("SpiraGPS/fonts/SignikaNegative-Medium.ttf", "ReadexPro", FontWeight.Normal)

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
        "All",

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
        "↑",
        "↖",
        "↘",
        "↗",
        "↙"
    )

    fun addKeywords(newKeywords: ArrayList<String>) {
        newKeywords.forEach {
            if(!keywords.contains(it))
                keywords.add(it)
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
}