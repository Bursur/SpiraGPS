package spiragps.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.ConditionState
import spiragps.data.route.Introduction
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText

@Composable
fun IntroductionView(intro: Introduction, conditionState: ConditionState) {
    val dividerColour = animateColorAsState(SpiraGPSColours.background)

    Column(modifier = Modifier.fillMaxWidth(.65f).padding(bottom = 5.dp)) {
        intro.entries.forEach { it ->
            val changes = conditionState.lastChange
            val showEntry = conditionState.areConditionsMet(it.requirement)

            AnimatedVisibility(visible = showEntry) {
                Column {
                    createEntry(entry = it)

                    if (it.trailingBreak)
                        Divider(color = dividerColour.value, thickness = 15.dp)
                }
            }
        }
    }
}