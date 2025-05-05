package com.bursur.spiragps.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import com.bursur.spiragps.theme.SpiraGPSAnimationsEnabled

@Composable
fun AnimatableContent(visible: Boolean, content: @Composable () -> Unit) {
    if(SpiraGPSAnimationsEnabled) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            content()
        }
    }
    else {
        if (visible) {
            content()
        }
    }
}