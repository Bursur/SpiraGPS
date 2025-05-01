package com.bursur.spiragps.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bursur.spiragps.route.data.Entry
import com.seiko.imageloader.rememberImagePainter
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap


@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageView(entry: Entry) {
    if(entry.image.isNotEmpty()) {
        Image(
            painter = rememberImagePainter(if (!entry.image.contains("http")) "https://bursur.github.io/${entry.image}" else entry.image),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(entry.scale)
        )
    }
    else if(entry.imageData.isNotEmpty()) {
        val data = ByteArray(entry.imageData.size)
        entry.imageData.forEachIndexed { index, item ->
            data[index] = item.toByte()
        }

        //Text(text = entry.imageData)
        Image(data.decodeToImageBitmap(), null)
    }
}