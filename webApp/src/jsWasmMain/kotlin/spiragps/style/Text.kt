package spiragps.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

val small = TextStyles(
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
        fontSize = 24.sp
    ),
    chapterTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
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

val medium = TextStyles(
    info = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    infoBold = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    bulletTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bulletTitleBold = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bulletPoint = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),


    routeTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    chapterTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
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

val large = TextStyles(
    info = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    infoBold = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),

    bulletTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bulletTitleBold = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bulletPoint = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),


    routeTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    chapterTitle = TextStyle(
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
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