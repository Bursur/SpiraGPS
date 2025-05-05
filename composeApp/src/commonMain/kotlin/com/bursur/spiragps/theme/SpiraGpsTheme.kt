package com.bursur.spiragps.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.bursur.spiragps.preferences.getAnimationsEnabledPreference

var SpiraGPSAnimationsEnabled by mutableStateOf(getAnimationsEnabledPreference() == 1)

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