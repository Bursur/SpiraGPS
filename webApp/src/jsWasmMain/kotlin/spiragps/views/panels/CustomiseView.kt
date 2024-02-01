package spiragps.views.panels

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.BulletedList

@Composable
fun CustomiseView(entry: Entry) {
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    BasePanelView("CUSTOMISE", border = SpiraGPSColours.value.customiseBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            Text(
                text = entry.item,
                style = SpiraGPSText.typography.info,
                color = textColour.value,
                fontWeight = FontWeight.Bold
            )

            BulletedList(entry)
        }
    }
}