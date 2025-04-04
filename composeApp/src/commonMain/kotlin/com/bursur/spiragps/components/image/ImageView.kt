package com.bursur.spiragps.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bursur.spiragps.route.data.Entry
import com.seiko.imageloader.rememberImagePainter

@Composable
fun ImageView(entry: Entry) {
    Image(
        painter = rememberImagePainter(if(!entry.image.contains("http")) "https://bursur.github.io/${entry.image}" else entry.image),
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth(entry.scale)
    )
}