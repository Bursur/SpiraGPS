package com.bursur.spiragps.theme

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight

fun highlightKeywords(text: String): AnnotatedString {
    val builder = AnnotatedString.Builder(text)

    return highlightKeywords(builder.toAnnotatedString())
}

fun highlightKeywords(text: AnnotatedString): AnnotatedString {
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
                    fontFamily = if(SpiraGPSText.useDyslexicFont) SpiraGPSText.dyslexicFont else SpiraGPSText.fontFamily,
                    color = SpiraGPSText.getKeywordColour(it),
                    fontWeight = FontWeight.Bold
                ),
                start = index,
                end = index + it.length
            )
        }
    }

    highlightSpecialCharacters(text.toString(), builder)

    return builder.toAnnotatedString()
}

private fun highlightSpecialCharacters(text: String, builder: AnnotatedString.Builder) {
    SpiraGPSText.specialCharacters.forEach {
        var start = 0
        while(start < text.length) {
            val index = text.indexOf(it, start)
            if(index == -1)
                break

            start += it.length

            builder.addStyle(
                style = SpanStyle(
                    fontFamily = SpiraGPSText.specialFont,
                    color = SpiraGPSText.getKeywordColour(it),
                    fontWeight = FontWeight.Bold,
                    fontSize = SpiraGPSText.typography.info.fontSize
                ),
                start = index,
                end = index + it.length
            )
        }
    }
}