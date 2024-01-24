package spiragps.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.route.Chapter
import spiragps.data.route.ConditionState
import spiragps.data.route.ContentsState
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours

@Composable
fun ChapterView(chapter: Chapter, conditionState: ConditionState, titlePositionCallback: (String, Float) -> Unit = { _: String, _: Float -> }) {
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TitleView(title = chapter.title, modifier = Modifier.clickable { expanded = !expanded }) { name: String, position: Float ->
            titlePositionCallback(name, position)
        }

        AnimatedVisibility(visible = expanded) {
            Column (modifier = Modifier.padding(vertical = 10.dp)) {
                chapter.entries.forEach { entry: Entry ->
                    // Changes in state can't be picked up on a map, so we use lastChange to force re-composition. Feels Hacky look for a better option
                    val changes = conditionState.lastChange

                    val showEntry = if(entry.requirement == null)
                        true
                    else
                        conditionState.conditions[entry.requirement.condition] == entry.requirement.state

                    AnimatedVisibility(visible = showEntry) {
                        Column {
                            createEntry(entry = entry)

                            if (entry.trailingBreak)
                                Divider(
                                    color = SpiraGPSColours.background,
                                    modifier = Modifier.padding(vertical = 10.dp)
                                )
                        }
                    }
                }
            }
        }
    }
}