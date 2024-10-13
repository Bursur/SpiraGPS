package com.bursur.spiragps.route.entries.encounter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletedList
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.BasePanelView
import com.bursur.spiragps.theme.SpiraGPSColours

@Composable
fun EncounterView(entry: Entry) {
    BasePanelView(title = "ENCOUNTERS", border = SpiraGPSColours.encounterBorder, minimised = entry.minimised) {
        Box(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            BulletedList(entry)
        }
    }
}