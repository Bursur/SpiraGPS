package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours

@Composable
fun CustomiseView(item: String, upgrades: ArrayList<String>) {
    BasePanelView("CUSTOMISE", border = SpiraGPSColours.customiseBorder) {
        Column(modifier = Modifier.padding(vertical = 30.dp)) {
            Text(
                text = item,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 10.dp, start = 40.dp, end = 40.dp)
            )

            BulletedList(points = upgrades)
        }
    }
}