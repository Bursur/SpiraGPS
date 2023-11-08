package spiragps.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

@Stable
interface ConditionState {
    var conditions: MutableMap<String, Boolean>
}

private class ConditionsStateImpl(conditions: MutableMap<String, Boolean> = mutableMapOf()) : ConditionState {
    override var conditions: MutableMap<String, Boolean> by mutableStateOf(conditions)

    companion object {
        val saver = Saver<ConditionsStateImpl, List<Any>>(
            save = { listOf(it.conditions) },
            restore = {
                ConditionsStateImpl(it[0] as MutableMap<String, Boolean>)
            }
        )
    }
}

@Composable
fun rememberConditionState(conditions: ArrayList<Condition>): ConditionState = rememberSaveable(
    saver = ConditionsStateImpl.saver
) {
    val map: MutableMap<String, Boolean> = mutableMapOf()
    conditions.forEach {
        map[it.name] = it.defaultState
    }
    ConditionsStateImpl(map)
}