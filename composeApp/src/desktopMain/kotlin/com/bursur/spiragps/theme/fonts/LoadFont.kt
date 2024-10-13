package com.bursur.spiragps.theme.fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import com.bursur.spiragps.utils.loadResource

actual suspend fun loadFont(url: String, name: String, weight: FontWeight): FontFamily =
    FontFamily(
        Font(
            identity = name,
            data = loadResource(url),
            weight = weight
        )
    )