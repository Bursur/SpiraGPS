package spiragps.views.editor.panels

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.views.createEntry
import spiragps.views.editor.EntryEditorButton

@Composable
fun TipEditorPanel(entry: Entry) {
    val entries by remember { mutableStateOf(entry.entries) }
    var updates by remember { mutableStateOf(0) }

    BasePanelEditor(border = SpiraGPSColours.value.tipBorder) {
        key(updates) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(items = entries) {
                    createEntry(it)
                }

                item {
                    EntryEditorButton(entry = Entry(type = "info")) {
                        if (it != null) {
                            entry.entries.add(it)
                            ++updates
                        }
                    }
                }
            }
        }
    }
}