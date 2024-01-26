package spiragps.views.editor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun TitleEditor(title: String, onValueChange: (String) -> Unit) {
    TextField(
        value = title,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            fontFamily = SpiraGPSText.fontFamily,
            fontSize = 24.sp,
            textAlign = TextAlign.Right
        ),
        placeholder = {
            Text(
                text = "Enter Title...",
                fontFamily = SpiraGPSText.fontFamily,
                fontSize = 24.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
    Divider(
        color = SpiraGPSColours.black,
        thickness = 2.dp,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}