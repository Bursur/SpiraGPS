package com.bursur.spiragps.theme.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.bursur.spiragps.R

private var fontMap: Map<String, Int> = mapOf(
    Pair("Kanit", R.font.kanit_light),
    Pair("ReadexPro", R.font.lexend_extra_light)
)

actual suspend fun loadFont(
    url: String,
    name: String,
    weight: FontWeight
): FontFamily = FontFamily(
        Font(
            resId = fontMap[name] ?: R.font.kanit_light,
            weight = weight
        )
    )