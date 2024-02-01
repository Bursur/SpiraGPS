package spiragps.views.editor.panels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import spiragps.style.SpiraGPSColours

@Composable
fun BasePanelEditor(border: Color, content: @Composable () -> Unit) {
    Surface(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, border),
        color = SpiraGPSColours.value.infoBackground,
    ) {
        Column {
            Divider(color = border, thickness = 20.dp)
            content()
        }
    }
}