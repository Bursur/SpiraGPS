package spiragps.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import spiragps.data.route.Condition
import spiragps.data.route.ConditionState

@Composable
fun HeaderView(conditions: ArrayList<Condition>, conditionState: ConditionState) {
    Row {
        // Icon

        // Conditionals
        conditions.forEach {
            ConditionalView(it, conditionState)
        }
    }
}