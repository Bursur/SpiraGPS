package com.bursur.spiragps.editor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bursur.spiragps.route.conditions.ConditionSelector
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours

@Composable
fun ControlPanel(
    entry: Entry,
    conditions: ArrayList<Condition>,
    onEntryDeleted: (Entry) -> Unit,
    onMoveUp: (Entry) -> Unit,
    onMoveDown: (Entry) -> Unit
) {
    var conditionExpanded by remember { mutableStateOf(false) }

    Row {
        // Conditions
        ControlPanelButton("https://bursur.github.io/SpiraGPS/todo.png") {
            conditionExpanded = true
        }

        // Start Minimised (Panel only)
        if(isPanel(entry)) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null
            )
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
        AsyncImage(model = icon, contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
    }
}