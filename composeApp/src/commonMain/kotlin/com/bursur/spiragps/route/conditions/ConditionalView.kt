package com.bursur.spiragps.route.conditions

import androidx.compose.animation.animateColorAsState
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
    val textColour = animateColorAsState(SpiraGPSColours.text)

    val selectedColour = animateColorAsState(SpiraGPSColours.toggleSelectedThumbColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(text = condition.name, style = SpiraGPSText.typography.conditionLabel, color = textColour.value)
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                conditionState.setCondition(condition.name, checked)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = selectedColour.value,
                checkedTrackColor = SpiraGPSColours.toggleSelectedTrackColour,
                uncheckedTrackColor = unselectedColour.value,
                uncheckedThumbColor = SpiraGPSColours.toggleUnselectedThumbColour,
            )
        )
    }
}