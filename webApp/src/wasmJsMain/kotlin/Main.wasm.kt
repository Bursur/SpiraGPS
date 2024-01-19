import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import spiragps.style.SpiraGPSText
import kotlin.wasm.unsafe.*

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Spira GPS") {
        var loading by remember { mutableStateOf(true) }

        if(!loading)
            SpiraGPS()

        LaunchedEffect(Unit) {
            SpiraGPSText.loadFonts()
            loading = false
        }
    }
}
