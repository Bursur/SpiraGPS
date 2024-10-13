package com.bursur.spiragps.theme.fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

expect suspend fun loadFont(url: String, name: String, weight: FontWeight): FontFamily