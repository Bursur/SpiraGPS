package spiragps.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import spiragps.data.Condition
import spiragps.data.ConditionState

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