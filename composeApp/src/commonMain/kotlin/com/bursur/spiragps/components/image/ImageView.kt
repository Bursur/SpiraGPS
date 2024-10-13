package com.bursur.spiragps.components.image

import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import com.bursur.spiragps.route.data.Entry

@Composable
fun ImageView(entry: Entry) {
    AsyncImage(
        model = "https://bursur.github.io/${entry.image}",
        contentDescription = ""
    )
}