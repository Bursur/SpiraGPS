package spiragps.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.style.getKeywordColour

fun highlightKeywords(text: String): AnnotatedString {
    val builder = AnnotatedString.Builder(text)

    SpiraGPSText.keywords.forEach {
        var start = 0
        while(start < text.length) {
            val index = text.indexOf(it, start)
            if(index == -1)
                break

            start += it.length

            builder.addStyle(
                style = SpanStyle(
                    color = getKeywordColour(it),
                    fontWeight = FontWeight.Bold
                ),
                start = index,
                end = index + it.length
            )
        }
    }

    return builder.toAnnotatedString()
}