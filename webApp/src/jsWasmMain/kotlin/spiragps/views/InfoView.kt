package spiragps.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSText
import spiragps.utils.highlightKeywords

@Composable
fun InfoView(modifier: Modifier = Modifier, entry: Entry) {
    Text(
        text = highlightKeywords(entry.text),
        fontFamily = SpiraGPSText.fontFamily,
        fontWeight = if (entry.bold) FontWeight.Bold else FontWeight.Normal,
        modifier = modifier.fillMaxWidth().padding(bottom = 5.dp)
    )
}