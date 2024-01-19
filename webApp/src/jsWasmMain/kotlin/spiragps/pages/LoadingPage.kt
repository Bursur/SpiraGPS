package spiragps.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours

@Composable
fun LoadingPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = SpiraGPSColours.loadingColour,
            trackColor = SpiraGPSColours.loadingTrack,
            modifier = Modifier
                .width(65.dp)
                .align(Alignment.Center)
        )
    }
}