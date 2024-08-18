package spiragps.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun TitleView(modifier: Modifier = Modifier, title: String, isChapter: Boolean = false, positionCallback: (Float) -> Unit = {_: Float -> }) {
    val textColour = animateColorAsState(SpiraGPSColours.text)
    Column(modifier = Modifier.fillMaxWidth(if(isChapter) 1f else .65f)) {
        Text(
            text = title,
            textAlign = TextAlign.Right,
            style = SpiraGPSText.typography.routeTitle,
            color = textColour.value,
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    positionCallback(it.positionInRoot().y)
                }
        )
        Divider(color = textColour.value, thickness = 2.dp)
    }
}