package spiragps.views

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
fun TitleView(modifier: Modifier = Modifier, title: String, positionCallback: (Float) -> Unit = {_: Float -> }) {
    Column {
        Text(
            text = title,
            textAlign = TextAlign.Right,
            style = SpiraGPSText.typography.routeTitle,
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    positionCallback(it.positionInRoot().y)
                }
        )
        Divider(color = SpiraGPSColours.black, thickness = 2.dp)
    }
}