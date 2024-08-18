package spiragps.views.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.route.NavigationState
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.utils.highlightKeywords

@Composable
fun BackButton(modifier: Modifier = Modifier, navigationState: NavigationState) {
    val textColour = animateColorAsState(SpiraGPSColours.text)
    Text(
        text = highlightKeywords("← Back"),
        style = SpiraGPSText.typography.backButton,
        color = textColour.value,
        modifier = modifier
            .padding(bottom = 20.dp)
            .clickable { navigationState.currentPage = NavigationState.LANDING }
    )
}