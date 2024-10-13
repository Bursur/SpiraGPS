package com.bursur.spiragps.route

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.theme.SpiraGPSColours

@Composable
fun BasePanelEditor(border: Color, content: @Composable () -> Unit) {
    Surface(
        shadowElevation =  5.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, border),
        color = SpiraGPSColours.infoBackground,
    ) {
        Column {
            HorizontalDivider(color = border, thickness = 20.dp)
            content()
        }
    }
}