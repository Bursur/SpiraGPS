package com.bursur.spiragps.route.entries.shop

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.BasePanelView
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.utils.formatWithCommas

@Composable
fun ShopView(entry: Entry) {
    BasePanelView(title = "SHOP - ${entry.cost.formatWithCommas()} GIL", border = SpiraGPSColours.shopBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            val textColour = animateColorAsState(SpiraGPSColours.text)
            if (entry.sell.isNotEmpty()) {
                Text("Sell:", style = SpiraGPSText.typography.infoBold, color = textColour.value)
                BulletedList(points = entry.sell)
            }

            if (entry.buy.isNotEmpty()) {
                Text("Buy:", style = SpiraGPSText.typography.infoBold, color = textColour.value)
                BulletedList(points = entry.buy)
            }
        }
    }
}