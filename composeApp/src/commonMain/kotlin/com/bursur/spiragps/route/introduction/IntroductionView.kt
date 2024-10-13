package com.bursur.spiragps.route.introduction

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.platform.isWebSite
import com.bursur.spiragps.route.conditions.ConditionState
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.data.Introduction

@Composable
fun IntroductionView(intro: Introduction, conditionState: ConditionState) {
    Column(
        modifier = Modifier
            .fillMaxWidth(if(isWebSite()) .75f else .95f)
            .padding(bottom = 5.dp)
    ) {
        intro.entries.forEach { it ->
            val changes = conditionState.lastChange
            val showEntry = conditionState.areConditionsMet(it.requirement)

            AnimatedVisibility(visible = showEntry) {
                Column {
                    createEntry(entry = it)

                    if (it.trailingBreak)
                        Spacer(modifier = Modifier.size(15.dp))
                }
            }
        }
    }
}