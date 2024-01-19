package spiragps.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import spiragps.data.NavigationState
import spiragps.style.SpiraGPSText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LandingPage(navigationState: NavigationState) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Spira GPS",
            fontFamily = SpiraGPSText.fontFamily,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 50.dp, bottom = 10.dp)
        )

        Text(
            text = "Select a route from the list below. You can click on the titles in the route to expand and contract those sections.",
            fontFamily = SpiraGPSText.fontFamily,
            textAlign = TextAlign.Center
        )

        FlowRow(maxItemsInEachRow = 3, modifier = Modifier.padding(top = 25.dp)) {
            Text(
                text = "CSR Any% w/Boosters",
                fontFamily = SpiraGPSText.fontFamily,
                fontSize = 25.sp,
                modifier = Modifier.clickable {
                    navigationState.currentPage = NavigationState.ROUTE
                }
            )
        }
    }
}