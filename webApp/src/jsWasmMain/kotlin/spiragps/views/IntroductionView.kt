package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Introduction
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun IntroductionView(intro: Introduction) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(intro.text, modifier = Modifier.fillMaxWidth(), fontFamily = SpiraGPSText.fontFamily)
        Divider(color = SpiraGPSColours.background, modifier = Modifier.padding(vertical = 10.dp))
    }
}