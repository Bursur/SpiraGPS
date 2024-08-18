package spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.flow.collect
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

external fun saveDarkModePreference(enabled: Int)
external fun saveTextSizePreference(size: Int)
external fun saveDyslexicModePreference(enabled: Int)

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }
    val tooltipState = remember { RichTooltipState() }
    val textColour = animateColorAsState(SpiraGPSColours.text)
    val bgColour = animateColorAsState(SpiraGPSColours.infoBackground)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    RichTooltipBox(
        title = { Text("Settings", style = SpiraGPSText.typography.infoBold, color = textColour.value) },
        text = {
            Column {
                val fontSize = when(SpiraGPSText.selectedFontSize) {
                    0 -> "Small"
                    1 -> "Medium"
                    else -> "Large"
                }
                Text("UI Theme: ${if(SpiraGPSDarkMode) "Dark" else "Light"}", style = SpiraGPSText.typography.info, color = textColour.value)
                Text("Font Size: $fontSize", style = SpiraGPSText.typography.info, color = textColour.value)
                Text("Lexend: ${if(SpiraGPSText.useDyslexicFont) "Enabled" else "Disabled"}", style = SpiraGPSText.typography.info, color = textColour.value)
            }
        },
        tooltipState = tooltipState,
        colors = TooltipDefaults.richTooltipColors(containerColor = bgColour.value),
    ) {
        SmallFloatingActionButton(
            onClick = {
                openAlertDialog = true
            },
            containerColor = SpiraGPSColours.fabBackgroundColour,
            contentColor = SpiraGPSColours.fabIconColour,
            modifier = modifier.tooltipAnchor().hoverable(interactionSource)
        ) {
            Image(painter = painterResource("SpiraGPS/settings.png"), contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
        }
    }

    LaunchedEffect(isHovered) {
        if(isHovered)
            tooltipState.show()
        else
            tooltipState.dismiss()
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
        val textColour = animateColorAsState(SpiraGPSColours.text)
        val bgColour = animateColorAsState(SpiraGPSColours.infoBackground)

        Card(colors = CardDefaults.cardColors(containerColor = bgColour.value, contentColor = bgColour.value)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Settings", style = SpiraGPSText.typography.chapterTitle, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(20.dp))
                Text("UI Theme", style = SpiraGPSText.typography.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                ThemeSelector()

                Text("Text Size", style = SpiraGPSText.typography.info, color = textColour.value, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 20.dp))
                TextSizeSelector()

                DyslexicSelector()

                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Close", style = SpiraGPSText.typography.info, color = textColour.value)
                }
            }
        }
    }
}

@Composable
private fun ThemeSelector() {
    val selectedColour = animateColorAsState(SpiraGPSColours.toggleSelectedThumbColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        ThemeSample("Light Mode", lightScheme)
        Switch(
            checked = SpiraGPSDarkMode,
            onCheckedChange = {
                SpiraGPSDarkMode = it
                SpiraGPSColours = if (SpiraGPSDarkMode) darkScheme else lightScheme
                saveDarkModePreference(if(SpiraGPSDarkMode) 1 else 0)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = selectedColour.value,
                checkedTrackColor = SpiraGPSColours.toggleSelectedTrackColour,
                checkedTrackAlpha = 1f,
                uncheckedTrackColor = unselectedColour.value,
                uncheckedThumbColor = SpiraGPSColours.toggleUnselectedThumbColour,
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
        Text(text = mode, style = SpiraGPSText.typography.info, color = colors.text, modifier = Modifier.padding(horizontal = 5.dp))
    }
}

@Composable
private fun TextSizeSelector() {
    Column {
        TextSizeRadioButton(size = "Small", selected = SpiraGPSText.selectedFontSize == 0, textStyle = if(SpiraGPSText.useDyslexicFont) dyslexicSmall.info else small.info) {
            if(SpiraGPSText.selectedFontSize != 0) {
                SpiraGPSText.selectedFontSize = 0
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                saveTextSizePreference(0)
            }
        }

        TextSizeRadioButton(size = "Medium", selected = SpiraGPSText.selectedFontSize == 1, textStyle = if(SpiraGPSText.useDyslexicFont) dyslexicMedium.info else medium.info) {
            if(SpiraGPSText.selectedFontSize != 1) {
                SpiraGPSText.selectedFontSize = 1
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                saveTextSizePreference(1)
            }
        }

        TextSizeRadioButton(size = "Large", selected = SpiraGPSText.selectedFontSize == 2, textStyle = if(SpiraGPSText.useDyslexicFont) dyslexicLarge.info else large.info) {
            if(SpiraGPSText.selectedFontSize != 2) {
                SpiraGPSText.selectedFontSize = 2
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                saveTextSizePreference(2)
            }
        }
    }
}

@Composable
private fun TextSizeRadioButton(size: String, selected: Boolean, textStyle: TextStyle, onClick: () -> Unit) {
    val selectedColour = animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)
    val textColour = animateColorAsState(SpiraGPSColours.text)

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
    val selectedColour = animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)
    val unselectedColour = animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)
    val textColour = animateColorAsState(SpiraGPSColours.text)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 20.dp)) {
        Switch(
            checked = SpiraGPSText.useDyslexicFont,
            onCheckedChange = {
                SpiraGPSText.useDyslexicFont = it
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                saveDyslexicModePreference(if(SpiraGPSText.useDyslexicFont) 1 else 0)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = SpiraGPSColours.toggleSelectedThumbColour,
                checkedTrackColor = selectedColour.value,
                checkedTrackAlpha = 1f,
                uncheckedTrackColor = unselectedColour.value,
                uncheckedThumbColor = SpiraGPSColours.toggleUnselectedThumbColour,
                uncheckedTrackAlpha = 1f
            ),
            modifier = Modifier.padding(start = 10.dp)
        )

        Text(
            text = "Use Lexend Fonts. (Helps if you have Dyslexia)",
            style = when(SpiraGPSText.selectedFontSize) {
                0 -> dyslexicSmall.info
                1 -> dyslexicMedium.info
                2 -> dyslexicLarge.info
                else -> dyslexicSmall.info
            },
            color = textColour.value
        )
    }
}