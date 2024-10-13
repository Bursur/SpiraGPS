package com.bursur.spiragps.route.entries.customise

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.BasePanelView
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun CustomiseView(entry: Entry) {
    val textColour = animateColorAsState(SpiraGPSColours.text)

    BasePanelView("CUSTOMISE", border = SpiraGPSColours.customiseBorder, minimised = entry.minimised) {
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