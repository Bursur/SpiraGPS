package spiragps.views.editor

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material3.DropdownMenu
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
import spiragps.data.route.Condition
import spiragps.data.route.Entry
import spiragps.data.route.Route
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@Composable
fun ConditionEditor(route: Route, onDismiss: () -> Unit) {
    val conditionList by remember { mutableStateOf(route.conditions) }
    var newCondition by remember { mutableStateOf("") }

    var updates by remember { mutableStateOf(0) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(elevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp).animateContentSize()
            ) {
                Text(text= "Update Conditions", style = SpiraGPSText.typography.routeTitle, color = SpiraGPSColours.text, modifier = Modifier.padding(bottom = 15.dp))

                key(updates) {
                    conditionList.forEachIndexed { index, condition ->
                        ConditionPointEditor(
                            text = condition.name,
                            enabled = condition.defaultState,
                            placeholderText = "Update Condition...",
                            onUpdated = {
                                conditionList[index].name = it
                                route.conditions = conditionList
                            },
                            onDeleted = {
                                conditionList.removeAt(index)
                                route.conditions = conditionList
                                ++updates
                            },
                            onToggle = {
                                condition.defaultState = it
                                ++updates
                            }
                        )
                    }
                }

                Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                    TextEdit(
                        text = newCondition,
                        placeholderText = "Enter New Condition...",
                        modifier = Modifier.weight(1f)
                    ) { newCondition = it }

                    TextButton(
                        onClick = {
                            conditionList.add(Condition(newCondition))
                            newCondition = ""
                            route.conditions = conditionList
                        },
                        modifier = Modifier.weight(.2f)
                    ) {
                        Text(
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
                }
            }
        }
    }
}