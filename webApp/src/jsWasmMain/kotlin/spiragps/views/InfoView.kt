package spiragps.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.Entry
import spiragps.utils.highlightKeywords

@Composable
fun InfoView(entry: Entry) {
    Text(
        text = highlightKeywords(entry.text),
        fontWeight = if (entry.bold) FontWeight.Bold else FontWeight.Normal,
        modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
    )
}