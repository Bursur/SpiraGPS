package com.bursur.spiragps.route.entries.battle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.BasePanelView
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.utils.formatWithCommas

@Composable
fun BattleView(entry: Entry) {
    val title = if(entry.health > 0)
        "${entry.enemy} - ${entry.health.formatWithCommas()} HP"
    else
        entry.enemy

    BasePanelView(title = title, border = SpiraGPSColours.battleBorder, minimised = entry.minimised) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(entry)
        }
    }
}