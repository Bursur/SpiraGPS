package com.bursur.spiragps.utils

actual suspend fun loadJsonFile(url: String): String = loadResource(url).decodeToString()