package spiragps.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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
            fontSize = 17.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 50.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = SpiraGPSText.fontFamily
        ),

        contentsTitle = TextStyle(
            fontSize = 20.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = 15.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        )
    )

val medium: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 29.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 29.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 55.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            fontFamily = SpiraGPSText.fontFamily
        ),

        contentsTitle = TextStyle(
            fontSize = 25.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = 20.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        )
    )

val large: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 44.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 60.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = SpiraGPSText.fontFamily
        ),

        contentsTitle = TextStyle(
            fontSize = 30.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = 25.sp,
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        )
    )

val dyslexicSmall: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 50.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = SpiraGPSText.dyslexicFont
        ),

        contentsTitle = TextStyle(
            fontSize = 20.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = 15.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        )
    )

val dyslexicMedium: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 29.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 29.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 55.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            fontFamily = SpiraGPSText.dyslexicFont
        ),

        contentsTitle = TextStyle(
            fontSize = 25.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = 20.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp
        )
    )

val dyslexicLarge: TextStyles
    get() = TextStyles(
        info = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        ),
        infoBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp
        ),

        bulletTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        ),
        bulletTitleBold = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp
        ),
        bulletPoint = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        ),


        routeTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            textAlign = TextAlign.Right
        ),
        chapterTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 44.sp,
            textAlign = TextAlign.Right
        ),

        landingTitle = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 60.sp
        ),

        backButton = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = SpiraGPSText.dyslexicFont
        ),

        contentsTitle = TextStyle(
            fontSize = 30.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Bold
        ),
        contentsEntry = TextStyle(
            fontSize = 25.sp,
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal
        ),

        conditionLabel = TextStyle(
            fontFamily = SpiraGPSText.dyslexicFont,
            fontWeight = FontWeight.Normal,
            fontSize = 27.sp
        )
    )