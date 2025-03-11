package com.bursur.spiragps.spherecounter

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Route
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun SphereCounterEditor(route: Route, onDismiss: () -> Unit) {
    var powerSpheres by remember { mutableStateOf(route.powerSpheres) }
    var speedSpheres by remember { mutableStateOf(route.speedSpheres) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(
            shadowElevation = 5.dp,
            shape = RoundedCornerShape(20.dp),
            color = SpiraGPSColours.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.9f).wrapContentHeight().padding(10.dp)
                    .animateContentSize()
            ) {
                Text(text= "Sphere Counts", style = SpiraGPSText.typography.routeTitle, color = SpiraGPSColours.text, modifier = Modifier.padding(bottom = 15.dp))
                Text(
                    text = "This should be used for the minimum number of Speed or Power spheres needed for the run. It's used to control the counters in the viewer.",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp)
                )

                // Speed
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 15.dp)) {
                    Text(
                        text = "Speed Spheres:",
                        style = SpiraGPSText.typography.infoBold,
                        color = SpiraGPSColours.text,
                        modifier = Modifier.padding(end = 15.dp)
                    )

                    TextEdit(
                        text = speedSpheres.toString(),
                        placeholderText = "0",
                        multiLine = false,
                        modifier = Modifier.width(40.dp).padding(end = 5.dp),
                        onEnterKey = {
                            try {
                                route.speedSpheres = speedSpheres.toInt()
                            }
                            catch(_: Exception) { speedSpheres = 0 }
                        }
                    ) {
                        try {
                            speedSpheres = it.toInt()
                            route.speedSpheres = speedSpheres.toInt()
                        }
                        catch(_: Exception) { speedSpheres = 0 }
                    }
                }

                // Strength
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Power Spheres:",
                        style = SpiraGPSText.typography.infoBold,
                        color = SpiraGPSColours.text,
                        modifier = Modifier.padding(end = 15.dp)
                    )

                    TextEdit(
                        text = powerSpheres.toString(),
                        placeholderText = "0",
                        multiLine = false,
                        modifier = Modifier.width(40.dp).padding(end = 5.dp),
                        onEnterKey = {
                            try {
                                route.speedSpheres = powerSpheres
                            }
                            catch(_: Exception) { powerSpheres = 0 }
                        }
                    ) {
                        try {
                            powerSpheres = it.toInt()
                            route.powerSpheres = powerSpheres
                        }
                        catch(_: Exception) { powerSpheres = 0 }
                    }
                }

                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Save", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                }
            }
        }
    }
}