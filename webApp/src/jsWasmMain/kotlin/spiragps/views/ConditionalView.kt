package spiragps.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import spiragps.data.Condition

@Composable
fun ConditionalView(condition: Condition) {
    var checked by remember { mutableStateOf(condition.defaultState) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = condition.name)
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}