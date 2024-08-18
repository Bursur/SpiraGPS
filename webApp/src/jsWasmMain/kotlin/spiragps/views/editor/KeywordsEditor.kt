package spiragps.views.editor

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import spiragps.data.route.Route
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@Composable
fun KeywordsEditorButton(modifier: Modifier = Modifier, route: Route, onDismiss: () -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = {
            openAlertDialog = true
        },
        modifier = modifier
    ) {
        Text(text = "Edit Keywords", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
    }

    if(openAlertDialog)
        KeywordsEditor(route) {
            openAlertDialog = false
            onDismiss()
        }
}

@Composable
fun KeywordsEditor(route: Route, onDismiss: () -> Unit) {
    val keywordList by remember { mutableStateOf(route.keywords) }
    var newKeyword by remember { mutableStateOf("") }

    var updates by remember { mutableStateOf(0) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(elevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp).animateContentSize()
            ) {
                Text(text= "Edit Keywords", style = SpiraGPSText.typography.routeTitle, color = SpiraGPSColours.text, modifier = Modifier.padding(bottom = 15.dp))

                key(updates) {
                    keywordList.forEachIndexed { index, word ->
                        BulletPointEditor(
                            text = word,
                            placeholderText = "Update Keyword...",
                            onUpdated = {
                                keywordList[index] = it
                                route.keywords = keywordList
                            },
                            onDeleted = {
                                keywordList.removeAt(index)
                                route.keywords = keywordList
                                ++updates
                            }
                        )
                    }
                }

                Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                    TextEdit(
                        text = newKeyword,
                        placeholderText = "Enter New Keyword...",
                        modifier = Modifier.weight(1f)
                    ) { newKeyword = it }

                    TextButton(
                        onClick = {
                            keywordList.add(newKeyword)
                            newKeyword = ""
                            route.keywords = keywordList
                        }
                    ) {
                        androidx.compose.material.Text(
                            text = "Add",
                            style = SpiraGPSText.typography.info,
                            color = SpiraGPSColours.text
                        )
                    }
                }

                // Save Button
                Row {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Save", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                    }

                    // Cancel Button
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Cancel", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                    }
                }
            }
        }
    }
}