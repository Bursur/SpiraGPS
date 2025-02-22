package com.bursur.spiragps.route.entries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun EntryEditorButton(modifier: Modifier = Modifier, entry: Entry, isPanel: Boolean = false, onDismiss: (Entry?) -> Unit) {
    var addEntryExpanded by remember { mutableStateOf(false) }

    val typeSelectedCallback: (String) -> Unit = { entryType: String ->
        entry.type = if (entryType == "text") "info" else entryType
        onDismiss(entry)
        addEntryExpanded = false
    }

    TextButton(
        onClick = {
            addEntryExpanded = true
        },
        modifier = modifier
    ) {
        Text(text ="Add Item", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
    }

    DropdownMenu(
        expanded = addEntryExpanded,
        onDismissRequest = { addEntryExpanded = false },
        modifier = Modifier.background(SpiraGPSColours.infoBackground)
    ) {
        EntryType("text", onClick = typeSelectedCallback)
        EntryType("image", onClick = typeSelectedCallback)
        EntryType("bullets", onClick = typeSelectedCallback)
        EntryType("table", onClick = typeSelectedCallback)
        if(!isPanel) {
            HorizontalDivider(
                color = SpiraGPSColours.text,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            EntryType("battle", onClick = typeSelectedCallback)
            EntryType("encounter", onClick = typeSelectedCallback)
            EntryType("trial", onClick = typeSelectedCallback)
            EntryType("shop", onClick = typeSelectedCallback)
            EntryType("equipment", onClick = typeSelectedCallback)
            EntryType("itemsort", onClick = typeSelectedCallback)
            EntryType("spheregrid", onClick = typeSelectedCallback)
            EntryType("customise", onClick = typeSelectedCallback)
            EntryType("blitzball", onClick = typeSelectedCallback)
            EntryType("tip", onClick = typeSelectedCallback)
        }
    }
}

@Composable
private fun EntryType(value: String, onClick: (String) -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = value,
                style = SpiraGPSText.typography.info,
                color = SpiraGPSColours.text
            )
        },
        onClick = {
            onClick(value)
        }
    )
}