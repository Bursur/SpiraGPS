package spiragps.views.components

import androidx.compose.runtime.*
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import spiragps.dialogs.HelpDialog
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun HelpActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.value.fabBackgroundColour,
        contentColor = SpiraGPSColours.value.fabIconColour,
        modifier = modifier
    ) {
        Text(text = "Help", fontFamily = SpiraGPSText.fontFamily)
    }

    if(openAlertDialog)
        HelpDialog { openAlertDialog = false }
}