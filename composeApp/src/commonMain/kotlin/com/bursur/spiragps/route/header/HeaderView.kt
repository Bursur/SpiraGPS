package com.bursur.spiragps.route.header

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.conditions.ConditionState
import com.bursur.spiragps.route.conditions.ConditionalView
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.theme.SpiraGPSColours

@Composable
fun HeaderView(conditions: ArrayList<Condition>, conditionState: ConditionState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            // Conditionals
            conditions.forEach {
                ConditionalView(it, conditionState)
            }
        }

        val textColour = animateColorAsState(SpiraGPSColours.text)
        HorizontalDivider(thickness = 1.dp, color = textColour.value, modifier = Modifier.fillMaxWidth(.65f))
    }
}