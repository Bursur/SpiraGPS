package spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.style.ColourScheme
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSDarkMode
import spiragps.style.SpiraGPSText
import spiragps.style.darkScheme
import spiragps.style.dyslexicLarge
import spiragps.style.dyslexicMedium
import spiragps.style.dyslexicSmall
import spiragps.style.large
import spiragps.style.lightScheme
import spiragps.style.medium
import spiragps.style.small

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SettingsActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.value.fabBackgroundColour,
        contentColor = SpiraGPSColours.value.fabIconColour,
        modifier = modifier
    ) {
        Image(painter = painterResource("SpiraGPS/settings.png"), contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
    }

    if(openAlertDialog)
        SettingsDialog { openAlertDialog = false }
}

@Composable
fun SettingsDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        val textColour = animateColorAsState(SpiraGPSColours.value.text)
        val bgColour = animateColorAsState(SpiraGPSColours.value.infoBackground)

        Card(colors = CardDefaults.cardColors(containerColor = bgColour.value, contentColor = bgColour.value)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Settings", style = SpiraGPSText.typography.value.chapterTitle, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(20.dp))
                Text("UI Theme", style = SpiraGPSText.typography.value.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                ThemeSelector()

                Text("Text Size", style = SpiraGPSText.typography.value.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 20.dp))
                TextSizeSelector()

                DyslexicSelector()

                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Close", style = SpiraGPSText.typography.value.info, color = textColour.value)
                }
            }
        }
    }
}

@Composable
private fun ThemeSelector() {
    var checked by remember { mutableStateOf(SpiraGPSDarkMode) }

    val selectedColour = animateColorAsState(SpiraGPSColours.value.toggleSelectedThumbColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.value.toggleUnselectedTrackColour)

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        ThemeSample("Light Mode", lightScheme)
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                SpiraGPSDarkMode = checked
                SpiraGPSColours.value = if (checked) darkScheme else lightScheme
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = selectedColour.value,
                checkedTrackColor = SpiraGPSColours.value.toggleSelectedTrackColour,
                checkedTrackAlpha = 1f,
                uncheckedTrackColor = unselectedColour.value,
                uncheckedThumbColor = SpiraGPSColours.value.toggleUnselectedThumbColour,
                uncheckedTrackAlpha = 1f
            )
        )
        ThemeSample("DarkMode", darkScheme)
    }
}

@Composable
private fun ThemeSample(mode: String, colors: ColourScheme) {
    Surface(
        shadowElevation = 5.dp,
        color = colors.infoBackground,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(text = mode, style = SpiraGPSText.typography.value.info, color = colors.text, modifier = Modifier.padding(horizontal = 5.dp))
    }
}

@Composable
private fun TextSizeSelector() {
    Column {
        TextSizeRadioButton(size = "Small", selected = SpiraGPSText.selectedFontSize.value == 0, textStyle = if(SpiraGPSText.useDyslexicFont.value) dyslexicSmall.info else small.info) {
            if(SpiraGPSText.selectedFontSize.value != 0) {
                SpiraGPSText.selectedFontSize.value = 0
                SpiraGPSText.typography.value = SpiraGPSText.getUpdatedFont()
            }
        }

        TextSizeRadioButton(size = "Medium", selected = SpiraGPSText.selectedFontSize.value == 1, textStyle = if(SpiraGPSText.useDyslexicFont.value) dyslexicMedium.info else medium.info) {
            if(SpiraGPSText.selectedFontSize.value != 1) {
                SpiraGPSText.selectedFontSize.value = 1
                SpiraGPSText.typography.value = SpiraGPSText.getUpdatedFont()
            }
        }

        TextSizeRadioButton(size = "Large", selected = SpiraGPSText.selectedFontSize.value == 2, textStyle = if(SpiraGPSText.useDyslexicFont.value) dyslexicLarge.info else large.info) {
            if(SpiraGPSText.selectedFontSize.value != 2) {
                SpiraGPSText.selectedFontSize.value = 2
                SpiraGPSText.typography.value = SpiraGPSText.getUpdatedFont()
            }
        }
    }
}

@Composable
private fun TextSizeRadioButton(size: String, selected: Boolean, textStyle: TextStyle, onClick: () -> Unit) {
    val selectedColour = animateColorAsState(SpiraGPSColours.value.toggleSelectedTrackColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.value.toggleUnselectedTrackColour)
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick() }) {
        RadioButton(
            selected = selected,
            onClick = { onClick() },
            colors = RadioButtonDefaults.colors(
                selectedColor = selectedColour.value,
                unselectedColor = unselectedColour.value
            )
        )
        Text(text = size, style = textStyle, color = textColour.value)
    }
}

@Composable
private fun DyslexicSelector() {
    val selectedColour = animateColorAsState(SpiraGPSColours.value.toggleSelectedTrackColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.value.toggleUnselectedTrackColour)
    val textColour = animateColorAsState(SpiraGPSColours.value.text)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 20.dp)) {
        Switch(
            checked = SpiraGPSText.useDyslexicFont.value,
            onCheckedChange = {
                SpiraGPSText.useDyslexicFont.value = it

                SpiraGPSText.typography.value = SpiraGPSText.getUpdatedFont()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = SpiraGPSColours.value.toggleSelectedThumbColour,
                checkedTrackColor = selectedColour.value,
                checkedTrackAlpha = 1f,
                uncheckedTrackColor = unselectedColour.value,
                uncheckedThumbColor = SpiraGPSColours.value.toggleUnselectedThumbColour,
                uncheckedTrackAlpha = 1f
            ),
            modifier = Modifier.padding(start = 10.dp)
        )

        Text(
            text = "Use Readex Fonts for Dyslexia (Some Glyphs are missing, mostly Arrows)",
            style = when(SpiraGPSText.selectedFontSize.value) {
                0 -> dyslexicSmall.info
                1 -> dyslexicMedium.info
                2 -> dyslexicLarge.info
                else -> dyslexicSmall.info
            },
            color = textColour.value
        )
    }
}