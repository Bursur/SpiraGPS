package spiragps.views.panels

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun BasePanelView(title: String, border: Color, minimised: Boolean = false, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(!minimised) }

    val borderColour = animateColorAsState(border)
    val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Surface(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, borderColour.value),
        color = bgColour.value,
    ) {
        Column {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = SpiraGPSText.typography.value.info,
                color = textColour.value,
                modifier = Modifier
                    .background(borderColour.value)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        expanded = !expanded
                    }
            )

            AnimatedVisibility(expanded) {
                content()
            }
        }
    }
}