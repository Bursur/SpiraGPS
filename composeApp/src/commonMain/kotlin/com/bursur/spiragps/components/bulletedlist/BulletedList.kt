package com.bursur.spiragps.components.bulletedlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.highlightKeywords

private val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp), lineHeight = 24.sp)

@Composable
fun BulletedList(entry: Entry) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    Column(modifier = Modifier.fillMaxWidth()) {
        if(entry.text.isNotEmpty())
            Text(
                highlightKeywords(entry.text),
                style = if(entry.bold) SpiraGPSText.typography.bulletTitleBold else SpiraGPSText.typography.bulletTitle,
                color = textColour
            )

        Text(
            buildAnnotatedString {
                entry.guide.forEach {
                    withStyle(paragraphStyle) {
                        append(SpiraGPSText.BULLET_CHAR)
                        append(" ")
                        append(highlightKeywords(it))
                    }
                }
            },
            style = SpiraGPSText.typography.bulletPoint,
            color = textColour,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun BulletedList(title: String = "", points: ArrayList<String>) {
    val textColour by animateColorAsState(SpiraGPSColours.text)
    Column(modifier = Modifier.fillMaxWidth()) {
        if(title.isNotEmpty())
            Text(
                title,
                style = SpiraGPSText.typography.bulletTitle,
                color = textColour
            )

        Text(
            buildAnnotatedString {
                points.forEach {
                    withStyle(paragraphStyle) {
                        append(SpiraGPSText.BULLET_CHAR)
                        append(" ")
                        append(highlightKeywords(it))
                    }
                }
            },
            style = SpiraGPSText.typography.bulletPoint,
            color = textColour,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}