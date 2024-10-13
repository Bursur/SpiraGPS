package com.bursur.spiragps.utils

import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.readResourceBytes

@OptIn(InternalResourceApi::class)
internal actual suspend fun loadResource(resourcePath: String): ByteArray {
    return readResourceBytes(resourcePath)
}