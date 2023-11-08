package spiragps.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import spiragps.data.Condition

@Composable
fun HeaderView(conditions: ArrayList<Condition>) {
    Row {
        // Icon

        // Conditionals
        conditions.forEach {
            ConditionalView(it)
        }
    }
}