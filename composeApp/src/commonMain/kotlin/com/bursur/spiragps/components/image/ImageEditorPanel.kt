package com.bursur.spiragps.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ImageEditorPanel(entry: Entry, selectedEntry: Entry) {
    var url by remember { mutableStateOf(entry.image) }

    Column {
        ImageView(entry)

        if(selectedEntry == entry) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 10.dp)) {
                Text(text = "Image URL:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)

                TextEdit(text = url, placeholderText = "Enter URL...", multiLine = false) {
                    url = it
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