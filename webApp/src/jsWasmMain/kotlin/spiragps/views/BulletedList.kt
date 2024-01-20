package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSText
import spiragps.utils.highlightKeywords

private const val BULLET_CHAR = "\u2022"
private val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp), lineHeight = 24.sp)

@Composable
fun BulletedList(entry: Entry) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if(entry.text.isNotEmpty())
            Text(highlightKeywords(entry.text), fontWeight = if(entry.bold) FontWeight.Bold else FontWeight.Normal, fontFamily = SpiraGPSText.fontFamily)

        Text(
            buildAnnotatedString {
                entry.guide.forEach {
                    withStyle(paragraphStyle) {
                        append(BULLET_CHAR)
                        append(" ")
                        append(highlightKeywords(it))
                    }
                }
            },
            fontFamily = SpiraGPSText.fontFamily,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun BulletedList(title: String = "", points: ArrayList<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if(title.isNotEmpty())
            Text(title, fontFamily = SpiraGPSText.fontFamily)

        Text(
            buildAnnotatedString {
                points.forEach {
                    withStyle(paragraphStyle) {
                        append(BULLET_CHAR)
                        append(" ")
                        append(highlightKeywords(it))
                    }
                }
            },
            fontFamily = SpiraGPSText.fontFamily,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}