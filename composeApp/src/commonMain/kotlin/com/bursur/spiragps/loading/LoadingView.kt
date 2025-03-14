package com.bursur.spiragps.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun LoadingView(text: String = "") {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            if (text.isNotEmpty())
                Text(
                    text = text,
                    fontFamily = SpiraGPSText.fontFamily,
                    color = SpiraGPSColours.loadingText
                )

            CircularProgressIndicator(
                color = SpiraGPSColours.loadingColour,
                trackColor = SpiraGPSColours.loadingTrack,
                modifier = Modifier
                    .width(65.dp)
            )
        }
    }
}