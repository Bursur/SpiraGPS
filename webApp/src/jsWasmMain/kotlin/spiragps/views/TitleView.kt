package spiragps.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.route.ContentsState
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TitleView(modifier: Modifier = Modifier, title: String, contentsState: ContentsState) {
    val bringIntoViewRequester = BringIntoViewRequester()
    Column {
        Text(
            text = title,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontFamily = SpiraGPSText.fontFamily,
            fontSize = 24.sp,
            modifier = modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
        )
        Divider(color = SpiraGPSColours.black, thickness = 2.dp)
    }

    LaunchedEffect(contentsState.selectedChapter) {
        if(title == contentsState.selectedChapter)
            bringIntoViewRequester.bringIntoView()
    }
}