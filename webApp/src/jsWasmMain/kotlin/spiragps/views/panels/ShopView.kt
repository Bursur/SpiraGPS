package spiragps.views.panels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.utils.formatWithCommas
import spiragps.style.SpiraGPSColours
import spiragps.views.BulletedList

@Composable
fun ShopView(entry: Entry) {
    BasePanelView(title = "SHOP - ${entry.cost.formatWithCommas()}", border = SpiraGPSColours.shopBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            if (entry.buy.isNotEmpty()) {
                Text("Buy:", fontWeight = FontWeight.Bold)
                BulletedList(points = entry.buy)
            }

            if (entry.sell.isNotEmpty()) {
                Text("Sell:", fontWeight = FontWeight.Bold)
                BulletedList(points = entry.sell)
            }
        }
    }
}