package com.bursur.spiragps.components.discord

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter

@Composable
fun DiscordButton() {
    val uriHandler = LocalUriHandler.current

    Image(
        painter = rememberImagePainter("https://bursur.github.io/SpiraGPS/discord.png"),
        contentDescription = null,
        modifier = Modifier
            .width(100.dp)
            .clickable {
            uriHandler.openUri("https://discord.gg/X3qXHWG")
        }
    )
}