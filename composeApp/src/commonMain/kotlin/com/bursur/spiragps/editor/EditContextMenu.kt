package com.bursur.spiragps.editor

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.entries.EntryEditorButton
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun EditContextMenu(
    open: Boolean,
    entry: Entry,
    conditions: ArrayList<Condition>,
    onDismiss: () -> Unit,
    onEntryUpdated: () -> Unit,
    onEntryDeleted: (Entry) -> Unit,
    onMoveUp: (Entry) -> Unit,
    onMoveDown: (Entry) -> Unit
) {
    DropdownMenu(
        expanded = open,
        onDismissRequest = { onDismiss() },
        modifier = Modifier.background(SpiraGPSColours.infoBackground)
    ) {
        EntryEditorButton(entry = entry) { onEntryUpdated() }

        TextButton(onClick = { onEntryDeleted(entry) }) {
            Text(text = "Delete", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
        }
        TextButton(onClick = { onMoveUp(entry) }) {
            Text(text = "Move Up", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
        }
        TextButton(onClick = { onMoveDown(entry) }) {
            Text(text = "Move Down", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
        }
    }
}

fun isPanel(entry: Entry) = when(entry.type) {
    "info" -> false
    "image" -> false
    "bullets" -> false
    "table" -> false
    else -> true
}