package spiragps.views.editor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageEditorPanel(entry: Entry) {
    var url by remember { mutableStateOf(entry.image) }

    Column {
        // Text
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 20.dp)) {
            Text(text = "Image URL:", style = SpiraGPSText.typography.value.info)

            TextEdit(text = url, placeholderText = "Enter URL...") {
                url = it
                entry.image = url
            }
        }

        if(url.isNotEmpty())
            Image(
                painter = painterResource(url),
                contentDescription = ""
            )
    }
}