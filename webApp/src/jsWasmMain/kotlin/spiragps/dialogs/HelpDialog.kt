package spiragps.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import spiragps.style.SpiraGPSText

@Composable
fun HelpDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(500.dp)
                    .padding(10.dp)
            ) {
                Row {
                    Text(text = "Spira GPS", fontFamily = SpiraGPSText.fontFamily, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Text(text = "(v0.1.3)", fontFamily = SpiraGPSText.fontFamily)
                }
                Text(
                    text = "The intention of this tool is to gather routes in one place. " +
                        "It also provides a way to edit new or existing runs with a visual editor.\n\n" +
                        "To get started select a route from the landing page, from there you can " +
                        "jump around the run using the contents panel. Clicking the title of each " +
                        "section or panel will expand and contract it, toggling the options at the " +
                        "top of the screen will update the route on the fly without needing to reload " +
                        "the document",
                    fontFamily = SpiraGPSText.fontFamily
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Close", fontFamily = SpiraGPSText.fontFamily)
                }
            }
        }
    }
}