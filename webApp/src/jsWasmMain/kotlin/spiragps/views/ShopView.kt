package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import spiragps.utils.formatWithCommas
import spiragps.style.SpiraGPSColours

@Composable
fun ShopView(cost: Int, buy: ArrayList<String> = arrayListOf(), sell: ArrayList<String> = arrayListOf()) {
    BasePanelView(title = "SHOP - ${cost.formatWithCommas()}", border = SpiraGPSColours.shopBorder) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            if (buy.isNotEmpty()) {
                Text("Buy:", fontWeight = FontWeight.Bold)
                BulletedList(points = buy)
            }

            if (sell.isNotEmpty()) {
                Text("Sell:", fontWeight = FontWeight.Bold)
                BulletedList(points = sell)
            }
        }
    }
}