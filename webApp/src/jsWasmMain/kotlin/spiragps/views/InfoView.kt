package spiragps.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.utils.highlightKeywords

@Composable
fun InfoView(entry: Entry) {
    val textColour = animateColorAsState(SpiraGPSColours.value.text)
    Text(
        text = highlightKeywords(entry.text),
        style = if(entry.bold) SpiraGPSText.typography.value.bulletTitleBold else SpiraGPSText.typography.value.bulletTitle,
        color = textColour.value,
        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
    )
}