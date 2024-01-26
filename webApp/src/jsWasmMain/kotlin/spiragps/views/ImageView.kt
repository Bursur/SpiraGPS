package spiragps.views

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import spiragps.data.route.Entry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageView(entry: Entry) {
    Image(
        painter = painterResource(entry.image),
        contentDescription = ""
    )
}