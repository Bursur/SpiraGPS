package com.bursur.spiragps.route.entries.encounter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.BasePanelView
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.theme.SpiraGPSColours

@Composable
fun EncounterView(entry: Entry) {
    BasePanelView(title = "ENCOUNTERS", border = SpiraGPSColours.encounterBorder, minimised = entry.minimised) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 40.dp)) {
            entry.entries.forEach {
                createEntry(entry = it)

                if (it.trailingBreak) {
                    Spacer(modifier = Modifier.size(15.dp))
                }
            }
        }
    }
}