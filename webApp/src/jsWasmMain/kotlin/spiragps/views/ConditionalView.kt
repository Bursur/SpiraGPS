package spiragps.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Condition
import spiragps.data.route.ConditionState
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun ConditionalView(condition: Condition, conditionState: ConditionState) {
    var checked by remember { mutableStateOf(condition.defaultState) }
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    val selectedColour = animateColorAsState(SpiraGPSColours.value.toggleSelectedThumbColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.value.toggleUnselectedTrackColour)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 20.dp)) {
        Text(text = condition.name, style = SpiraGPSText.typography.value.conditionLabel, color = textColour.value)
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                conditionState.setCondition(condition.name, checked)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = selectedColour.value,
                checkedTrackColor = SpiraGPSColours.value.toggleSelectedTrackColour,
                checkedTrackAlpha = 1f,
                uncheckedTrackColor = unselectedColour.value,
                uncheckedThumbColor = SpiraGPSColours.value.toggleUnselectedThumbColour,
                uncheckedTrackAlpha = 1f
            )
        )
    }
}