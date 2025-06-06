package com.bursur.spiragps.route.entries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.AnimatableContent
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun BasePanelView(title: String, border: Color, minimised: Boolean = false, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(!minimised) }

    val borderColour by animateColorAsState(border)
    val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)
    val textColour by animateColorAsState(SpiraGPSColours.text)

    Surface(
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, borderColour),
        color = bgColour,
    ) {
        Column {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = SpiraGPSText.typography.info,
                color = textColour,
                modifier = Modifier
                    .background(borderColour)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        expanded = !expanded
                    }
            )

            AnimatableContent(expanded) {
                content()
            }
        }
    }
}