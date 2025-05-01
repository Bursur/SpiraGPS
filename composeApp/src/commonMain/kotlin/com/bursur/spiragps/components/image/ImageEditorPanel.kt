package com.bursur.spiragps.components.image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import io.github.vinceglb.filekit.core.FileKit
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import kotlinx.coroutines.launch

@Composable
fun ImageEditorPanel(entry: Entry, selectedEntry: Entry) {
    val scope = rememberCoroutineScope()
    var url by remember { mutableStateOf(entry.image) }
    var data by remember { mutableStateOf(entry.imageData) }

    Column {
        key(data, url) {
            ImageView(entry)
        }

        if(selectedEntry == entry) {
            TextButton(
                onClick = {
                    scope.launch {
                        FileKit.pickFile(
                            type = PickerType.File(extensions = listOf("png")),
                            mode = PickerMode.Single,
                            title = "Select an Image",
                            initialDirectory = ""
                        )?.let { file ->
                            url = ""
                            val bytes = file.readBytes()
                            data = bytes.map {
                                it.toInt()
                            }

                            entry.imageData = data
                            entry.image = url
                        }
                    }
                }
            ) {
                Text(
                    text = "Choose File...",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 10.dp)) {
                Text(text = "Image URL:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)

                TextEdit(text = url, placeholderText = "Enter URL...", multiLine = false) {
                    data = listOf()
                    url = it

                    entry.imageData = data
                    entry.image = url

                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 10.dp)) {
                var sliderPosition by remember { mutableFloatStateOf(entry.scale) }

                Text(text = "Scale:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        entry.scale = sliderPosition
                    },
                    steps = 9,
                    valueRange = .1f..1f,
                    colors = SliderDefaults.colors(
                        thumbColor = SpiraGPSColours.toggleSelectedThumbColour,
                        activeTrackColor = SpiraGPSColours.toggleSelectedTrackColour,
                        inactiveTrackColor = SpiraGPSColours.toggleUnselectedTrackColour
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )

                Text(text = "${(sliderPosition * 100).toInt()}%", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)
            }
        }
    }
}