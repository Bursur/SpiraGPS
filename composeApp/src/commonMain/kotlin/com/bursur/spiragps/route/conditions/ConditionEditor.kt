package com.bursur.spiragps.route.conditions

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.bursur.spiragps.components.bulletedlist.BulletPointEditor
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Condition
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ConditionEditor(route: Route, onDismiss: () -> Unit) {
    val conditionList by remember { mutableStateOf(route.conditions) }
    var newCondition by remember { mutableStateOf("") }

    var updates by remember { mutableStateOf(0) }
    var editingCondition by remember { mutableStateOf(Condition()) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(shadowElevation = 5.dp, shape = RoundedCornerShape(20.dp), color = SpiraGPSColours.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp).animateContentSize()
            ) {
                Text(text= "Update Conditions", style = SpiraGPSText.typography.routeTitle, color = SpiraGPSColours.text, modifier = Modifier.padding(bottom = 15.dp))

                key(updates) {
                    conditionList.forEach { condition ->
                        Text(
                            text = condition.name,
                            style = SpiraGPSText.typography.info,
                            color = SpiraGPSColours.text,
                            modifier = Modifier.clickable { editingCondition = condition }
                        )

                        if(editingCondition == condition)
                            ConditionPointEditor(condition) { editingCondition = Condition()}
                    }
                }

                Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                    TextEdit(
                        text = newCondition,
                        placeholderText = "Enter New Condition...",
                        multiLine = false,
                        onEnterKey = {
                            conditionList.add(Condition(newCondition))
                            newCondition = ""
                            editingCondition = conditionList.last()
                            route.conditions = conditionList
                        },
                        modifier = Modifier.weight(1f)
                    ) { newCondition = it }

                    TextButton(
                        onClick = {
                            conditionList.add(Condition(newCondition))
                            newCondition = ""
                            editingCondition = conditionList.last()
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