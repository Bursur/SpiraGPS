package com.bursur.spiragps.components.image

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.bursur.spiragps.route.data.Entry

@Composable
fun ImageView(entry: Entry) {
    AsyncImage(
        model = if(!entry.image.contains("http")) "https://bursur.github.io/${entry.image}" else entry.image,
        contentDescription = "",
        modifier = Modifier.fillMaxWidth(entry.scale)
    )
}