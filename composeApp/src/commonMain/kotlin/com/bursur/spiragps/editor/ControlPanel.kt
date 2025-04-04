package com.bursur.spiragps.editor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.conditions.ConditionSelector
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import com.seiko.imageloader.rememberImagePainter

@Composable
fun ControlPanel(
    entry: Entry,
    conditions: ArrayList<Condition>,
    onEntryDeleted: (Entry) -> Unit,
    onMoveUp: (Entry) -> Unit,
    onMoveDown: (Entry) -> Unit
) {
    var conditionExpanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(entry.minimised) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        // Conditions
        if(entry != secondaryEntry) {
            ControlPanelButton("https://bursur.github.io/SpiraGPS/todo.png") {
                conditionExpanded = true
            }
        }

        // Start Minimised (Panel only)
        if(isPanel(entry)) {
            ControlPanelToggle(selected) {
                selected = it
                entry.minimised = it
            }
        }

        // Up
        ControlPanelButton("https://bursur.github.io/SpiraGPS/up_arrow.png") {
            onMoveUp(entry)
        }

        // Delete
        ControlPanelButton("https://bursur.github.io/SpiraGPS/delete.png") {
            onEntryDeleted(entry)
        }

        // Down
        ControlPanelButton("https://bursur.github.io/SpiraGPS/down_arrow.png") {
            onMoveDown(entry)
        }

        if(conditionExpanded) {
            ConditionSelector(entry, conditions) {
                conditionExpanded = false
            }
        }
    }
}

@Composable
private fun ControlPanelButton(icon: String, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = SpiraGPSColours.fabIconColour,
            containerColor = SpiraGPSColours.fabBackgroundColour
        )
    ) {
        Image(rememberImagePainter(icon), contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
    }
}

@Composable
private fun ControlPanelToggle(selected: Boolean, onClick: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Start Minimised:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
        Checkbox(
            checked = selected,
            onCheckedChange = {
                onClick(it)
            },
            colors = CheckboxDefaults.colors(
                uncheckedColor = SpiraGPSColours.toggleUnselectedTrackColour,
                checkedColor = SpiraGPSColours.toggleSelectedTrackColour
            )
        )
    }
}