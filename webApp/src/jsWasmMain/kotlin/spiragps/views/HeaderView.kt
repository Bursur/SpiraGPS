package spiragps.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Condition
import spiragps.data.route.ConditionState
import spiragps.style.SpiraGPSColours

@Composable
fun HeaderView(conditions: ArrayList<Condition>, conditionState: ConditionState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            // Conditionals
            conditions.forEach {
                ConditionalView(it, conditionState)
            }
        }

        val textColour = animateColorAsState(SpiraGPSColours.value.text)
        Divider(thickness = 1.dp, color = textColour.value, modifier = Modifier.fillMaxWidth(.65f))
    }
}