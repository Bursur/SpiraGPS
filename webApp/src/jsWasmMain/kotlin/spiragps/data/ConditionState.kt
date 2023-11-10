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
    var lastChange: Int

    fun setCondition(name: String, enabled: Boolean)
}

private class ConditionsStateImpl(conditions: MutableMap<String, Boolean> = mutableMapOf(), lastChange: Int = 0) : ConditionState {
    override var conditions: MutableMap<String, Boolean> by mutableStateOf(conditions)
    override var lastChange: Int by mutableStateOf(lastChange)

    override fun setCondition(name: String, enabled: Boolean) {
        conditions[name] = enabled
        lastChange++
    }

    companion object {
        val saver = Saver<ConditionsStateImpl, List<Any>>(
            save = { listOf(it.conditions, it.lastChange) },
            restore = {
                ConditionsStateImpl(it[0] as MutableMap<String, Boolean>, it[1] as Int)
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