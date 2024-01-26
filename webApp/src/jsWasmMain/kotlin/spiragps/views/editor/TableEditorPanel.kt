package spiragps.views.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.TableView

@Composable
fun TableEditorPanel(entry: Entry, onUpdated: (Entry) -> Unit) {
    val entries: ArrayList<String> by remember { mutableStateOf(entry.guide) }
    var newItem by remember { mutableStateOf("") }

    Column {
        // Entries
        key(entries.size) {
            TableView(Entry(guide = entries, columns = 2))
        }

        Row {
            TextField(
                value = newItem,
                onValueChange = {
                    newItem = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = SpiraGPSColours.text,
                    backgroundColor = SpiraGPSColours.infoBackground,
                    focusedIndicatorColor = SpiraGPSColours.toggleSelectedTrackColour
                ),
                textStyle = TextStyle(fontFamily = SpiraGPSText.fontFamily,),
                placeholder = {
                    Text(
                        text = "Enter New Point...",
                        fontFamily = SpiraGPSText.fontFamily,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.weight(1f)
            )

            TextButton(
                onClick = {
                    entries.add(newItem)
                    newItem = ""
                    onUpdated(Entry(type = "table", guide = entries, columns = 2))
                }
            ) {
                Text(text = "Add", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = Color.Black))
            }
        }
    }
}