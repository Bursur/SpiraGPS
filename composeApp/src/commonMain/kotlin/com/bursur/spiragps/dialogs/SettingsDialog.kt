package com.bursur.spiragps.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bursur.spiragps.preferences.setDarkModePreference
import com.bursur.spiragps.preferences.setDyslexicModePreference
import com.bursur.spiragps.preferences.setKhaegarModePreference
import com.bursur.spiragps.preferences.setTextSizePreference
import com.bursur.spiragps.theme.ColourScheme
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSDarkMode
import com.bursur.spiragps.theme.SpiraGPSKhaegarMode
import com.bursur.spiragps.theme.SpiraGPSText
import com.bursur.spiragps.theme.darkScheme
import com.bursur.spiragps.theme.dyslexicLarge
import com.bursur.spiragps.theme.dyslexicMedium
import com.bursur.spiragps.theme.dyslexicSmall
import com.bursur.spiragps.theme.khaegarScheme
import com.bursur.spiragps.theme.large
import com.bursur.spiragps.theme.lightScheme
import com.bursur.spiragps.theme.medium
import com.bursur.spiragps.theme.small
import com.seiko.imageloader.rememberImagePainter

external fun playAudio(file: String)


@Composable
fun SettingsActionButton(modifier: Modifier = Modifier) {
    var openAlertDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    SmallFloatingActionButton(
        onClick = {
            openAlertDialog = true
        },
        containerColor = SpiraGPSColours.fabBackgroundColour,
        contentColor = SpiraGPSColours.fabIconColour,
        modifier = modifier.hoverable(interactionSource)
    ) {
        Image(rememberImagePainter("https://bursur.github.io/SpiraGPS/settings.png"), contentDescription = null, modifier = Modifier.padding(5.dp).width(24.dp).height(24.dp))
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
        val textColour by animateColorAsState(SpiraGPSColours.text)
        val bgColour by animateColorAsState(SpiraGPSColours.infoBackground)

        Card(colors = CardDefaults.cardColors(containerColor = bgColour, contentColor = bgColour)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Settings", style = SpiraGPSText.typography.chapterTitle, color = textColour, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(20.dp))
                Text("UI Theme", style = SpiraGPSText.typography.info, color = textColour, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                ThemeSelector()

                Text("Text Size", style = SpiraGPSText.typography.info, color = textColour, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 20.dp))
                TextSizeSelector()

                DyslexicSelector()

                // Khaegar Mode
                KhaegarModeButton()


                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Close", style = SpiraGPSText.typography.info, color = textColour)
                }
            }
        }
    }
}

@Composable
private fun KhaegarModeButton() {
    val selectedColour by animateColorAsState(SpiraGPSColours.toggleSelectedThumbColour)
    val unselectedColour by animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Switch(
            checked = SpiraGPSKhaegarMode,
            onCheckedChange = {
                SpiraGPSKhaegarMode = it
                SpiraGPSColours = when {
                    SpiraGPSKhaegarMode ->  khaegarScheme
                    SpiraGPSDarkMode -> darkScheme
                    else -> lightScheme
                }
                setKhaegarModePreference(if(SpiraGPSKhaegarMode) 1 else 0)

                if(it)
                    playAudio("https://bursur.github.io/SpiraGPS/moopsy.mp3")
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = selectedColour,
                checkedTrackColor = SpiraGPSColours.toggleSelectedTrackColour,
                uncheckedTrackColor = unselectedColour,
                uncheckedThumbColor = SpiraGPSColours.toggleUnselectedThumbColour,
            )
        )
        Image(
            rememberImagePainter("https://bursur.github.io/SpiraGPS/khaegar.png"),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.height(25.dp).padding(horizontal = 3.dp)
        )
        ThemeSample("Khaegar Mode", khaegarScheme)
    }
}

@Composable
private fun ThemeSelector() {
    val selectedColour by animateColorAsState(SpiraGPSColours.toggleSelectedThumbColour)
    val unselectedColour by animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        ThemeSample("Light Mode", lightScheme)
        Switch(
            checked = SpiraGPSDarkMode,
            onCheckedChange = {
                SpiraGPSDarkMode = it
                SpiraGPSColours = when {
                    SpiraGPSKhaegarMode ->  khaegarScheme
                    SpiraGPSDarkMode -> darkScheme
                    else -> lightScheme
                }
                setDarkModePreference(if(SpiraGPSDarkMode) 1 else 0)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = selectedColour,
                checkedTrackColor = SpiraGPSColours.toggleSelectedTrackColour,
                uncheckedTrackColor = unselectedColour,
                uncheckedThumbColor = SpiraGPSColours.toggleUnselectedThumbColour,
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
                setTextSizePreference(0)
            }
        }

        TextSizeRadioButton(size = "Medium", selected = SpiraGPSText.selectedFontSize == 1, textStyle = if(SpiraGPSText.useDyslexicFont) dyslexicMedium.info else medium.info) {
            if(SpiraGPSText.selectedFontSize != 1) {
                SpiraGPSText.selectedFontSize = 1
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                setTextSizePreference(1)
            }
        }

        TextSizeRadioButton(size = "Large", selected = SpiraGPSText.selectedFontSize == 2, textStyle = if(SpiraGPSText.useDyslexicFont) dyslexicLarge.info else large.info) {
            if(SpiraGPSText.selectedFontSize != 2) {
                SpiraGPSText.selectedFontSize = 2
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                setTextSizePreference(2)
            }
        }
    }
}

@Composable
private fun TextSizeRadioButton(size: String, selected: Boolean, textStyle: TextStyle, onClick: () -> Unit) {
    val selectedColour by animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)
    val unselectedColour by animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)
    val textColour by animateColorAsState(SpiraGPSColours.text)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick() }) {
        RadioButton(
            selected = selected,
            onClick = { onClick() },
            colors = RadioButtonDefaults.colors(
                selectedColor = selectedColour,
                unselectedColor = unselectedColour
            )
        )
        Text(text = size, style = textStyle, color = textColour)
    }
}

@Composable
private fun DyslexicSelector() {
    val selectedColour by animateColorAsState(SpiraGPSColours.toggleSelectedTrackColour)
    val unselectedColour by animateColorAsState(SpiraGPSColours.toggleUnselectedTrackColour)
    val textColour by animateColorAsState(SpiraGPSColours.text)

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 20.dp)) {
        Switch(
            checked = SpiraGPSText.useDyslexicFont,
            onCheckedChange = {
                SpiraGPSText.useDyslexicFont = it
                SpiraGPSText.typography = SpiraGPSText.getUpdatedFont()
                setDyslexicModePreference(if(SpiraGPSText.useDyslexicFont) 1 else 0)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = SpiraGPSColours.toggleSelectedThumbColour,
                checkedTrackColor = selectedColour,
                uncheckedTrackColor = unselectedColour,
                uncheckedThumbColor = SpiraGPSColours.toggleUnselectedThumbColour,
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
            color = textColour,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}