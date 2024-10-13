package com.bursur.spiragps.route.conditions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Requirement

@Stable
interface ConditionState {
    var conditions: MutableMap<String, Boolean>
    var lastChange: Int
    var sheetOpen: Boolean

    fun setCondition(name: String, enabled: Boolean)
    fun areConditionsMet(requirements: ArrayList<Requirement>): Boolean
}

private class ConditionsStateImpl(conditions: MutableMap<String, Boolean> = mutableMapOf(), lastChange: Int = 0, sheetOpen: Boolean = false) :
    ConditionState {
    override var conditions: MutableMap<String, Boolean> by mutableStateOf(conditions)
    override var lastChange: Int by mutableStateOf(lastChange)
    override var sheetOpen: Boolean by mutableStateOf(sheetOpen)

    override fun setCondition(name: String, enabled: Boolean) {
        conditions[name] = enabled
        lastChange++
    }

    override fun areConditionsMet(requirements: ArrayList<Requirement>): Boolean {
        requirements.forEach {
            if(it.condition.isNotEmpty()) {
                if(conditions[it.condition] != it.state)
                    return false
            }
        }

        return true
    }

    companion object {
        val saver = Saver<ConditionsStateImpl, List<Any>>(
            save = { listOf(it.conditions, it.lastChange) },
            restore = {
                ConditionsStateImpl(it[0] as MutableMap<String, Boolean>, it[1] as Int, it[2] as Boolean)
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