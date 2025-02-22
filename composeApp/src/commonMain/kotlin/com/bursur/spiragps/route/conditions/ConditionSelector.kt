package com.bursur.spiragps.route.conditions

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.data.Requirement
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

fun getConditionString(entry: Entry): String {
    return if(entry.requirement.isNotEmpty()) {
        var ret = ""

        entry.requirement.forEach {
            ret += "${it.condition}: ${it.state} "
        }

        ret
    }
    else
        "not set"
}

@Composable
fun ConditionSelector(entry: Entry, conditions: ArrayList<Condition>, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(shadowElevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.background) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp).animateContentSize()
            ) {
                item {
                    Text(
                        text = "Name / Use? / Required State",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }

                items(conditions) {
                    ConditionItem(
                        condition = it,
                        entry = entry
                    )
                }

                item {
                    TextButton(onClick = { onDismiss() }) {
                        Text(
                            text = "Save",
                            style = SpiraGPSText.typography.info,
                            color = SpiraGPSColours.text
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ConditionItem(condition: Condition, entry: Entry) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        var isUsed by remember { mutableStateOf(entry.hasRequirement(condition.name)) }
        var requiredState by remember { mutableStateOf(if(isUsed) entry.getRequirementState(condition.name) else if(condition.options.isNotEmpty()) condition.options[0] else "<not set>") }
        var selectorOpen by remember { mutableStateOf(false) }

        // Name
        Text(
            text = condition.name,
            style = SpiraGPSText.typography.info,
            color = SpiraGPSColours.text,
            modifier = Modifier.padding(bottom = 10.dp).weight(1f)
        )

        // Use
        Checkbox(
            checked = isUsed,
            onCheckedChange = { used ->
                isUsed = used

                if(entry.hasRequirement(condition.name))
                    entry.requirement.removeAll { it.condition == condition.name }
                else
                    entry.requirement.add(Requirement(condition.name, requiredState))
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = SpiraGPSColours.toggleUnselectedTrackColour,
                checkedColor = SpiraGPSColours.toggleSelectedTrackColour
            )
        )

        // Required State

        // Name
        Text(
            text = requiredState,
            style = SpiraGPSText.typography.info,
            color = SpiraGPSColours.text,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .weight(1f)
                .clickable {
                    selectorOpen = true
                }
        )

        ConditionSelectDropdown(
            condition,
            selectorOpen,
            onDismiss = {},
            onSelected = { state ->
                selectorOpen = false
                requiredState = state
                entry.requirement.firstOrNull { it.condition == condition.name }?.state = state
            }
        )
    }
}

@Composable
fun ConditionSelectDropdown(condition: Condition, open: Boolean, onDismiss: () -> Unit, onSelected: (String) -> Unit) {
    DropdownMenu(
        expanded = open,
        onDismissRequest = { onDismiss() },
        modifier = Modifier.background(SpiraGPSColours.infoBackground)
    ) {
        condition.options.forEach {
            TextButton(onClick = { onSelected(it) }) {
                Text(
                    text = it,
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
            }
        }
    }
}