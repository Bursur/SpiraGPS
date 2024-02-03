package spiragps.views.components

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.editor.EntryEditorButton
import spiragps.views.editor.panels.PanelEditorButton

@Composable
fun EditContextMenu(
    open: Boolean,
    entry: Entry,
    onDismiss: () -> Unit,
    onEntryUpdated: () -> Unit,
    onEntryDeleted: (Entry) -> Unit,
    onMoveUp: (Entry) -> Unit,
    onMoveDown: (Entry) -> Unit
) {
    DropdownMenu(
        expanded = open,
        onDismissRequest = { onDismiss() },
        modifier = Modifier.background(SpiraGPSColours.value.infoBackground)
    ) {
        if(!isPanel(entry))
            EntryEditorButton(entry = entry, isEditButton = true) { onEntryUpdated() }
        else
            PanelEditorButton(entry = entry, isEditButton = true) { onEntryUpdated() }
        TextButton(onClick = { onEntryDeleted(entry) }) {
            Text(text = "Delete", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
        }
        TextButton(onClick = { onMoveUp(entry) }) {
            Text(text = "Move Up", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
        }
        TextButton(onClick = { onMoveDown(entry) }) {
            Text(text = "Move Down", style = SpiraGPSText.typography.value.info, color = SpiraGPSColours.value.text)
        }
    }
}

private fun isPanel(entry: Entry) = when(entry.type) {
    "info" -> false
    "image" -> false
    "bullets" -> false
    "table" -> false
    else -> true
}