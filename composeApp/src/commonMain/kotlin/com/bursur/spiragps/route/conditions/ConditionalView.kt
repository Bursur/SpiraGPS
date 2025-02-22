package com.bursur.spiragps.route.conditions

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ConditionalView(condition: Condition, conditionState: ConditionState) {
    var checked by remember { mutableStateOf(conditionState.conditions[condition.name] ?: false) }
    val textColour by animateColorAsState(SpiraGPSColours.text)

    val selectedColour = animateColorAsState(SpiraGPSColours.toggleSelectedThumbColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)

    var optionsExpanded by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(text = condition.name, style = SpiraGPSText.typography.conditionLabel, color = textColour)
        Text(
            text = conditionState.conditions[condition.name].orEmpty(),
            style = SpiraGPSText.typography.conditionLabel,
            color = textColour,
            modifier = Modifier.clickable {
                optionsExpanded = true
            }
        )

        ConditionSelectDropdown(
            condition,
            optionsExpanded,
            onDismiss = {},
            onSelected = { state ->
                optionsExpanded = false
                conditionState.setCondition(condition.name, state)
            }
        )
    }
}