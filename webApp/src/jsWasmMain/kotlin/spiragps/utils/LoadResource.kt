package spiragps.utils

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
internal suspend fun loadResource(resourcePath: String): ByteArray {
    return resource(resourcePath).readBytes()
}