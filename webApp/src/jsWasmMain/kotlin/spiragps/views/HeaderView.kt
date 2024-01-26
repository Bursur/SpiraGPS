package spiragps.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

        Divider(thickness = 1.dp, color = SpiraGPSColours.text)
    }
}