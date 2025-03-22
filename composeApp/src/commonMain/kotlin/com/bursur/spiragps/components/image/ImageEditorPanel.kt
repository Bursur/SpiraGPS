package com.bursur.spiragps.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageEditorPanel(entry: Entry, selectedEntry: Entry) {
    var url by remember { mutableStateOf(entry.image) }

    Column {
        ImageView(entry)

        if(selectedEntry == entry) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 20.dp)) {
                Text(text = "Image URL:", style = SpiraGPSText.typography.info, color = SpiraGPSColours.text)

                TextEdit(text = url, placeholderText = "Enter URL...", multiLine = false) {
                    url = it
                    entry.image = url
                }
            }
        }
    }
}