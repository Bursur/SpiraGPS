package spiragps.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import spiragps.data.Chapter
import spiragps.data.ConditionState
import spiragps.data.ContentsState
import spiragps.data.Entry
import spiragps.style.SpiraGPSColours

@Composable
fun ChapterView(chapter: Chapter, conditionState: ConditionState, contentsState: ContentsState) {
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        TitleView(title = chapter.title, contentsState = contentsState, modifier = Modifier.clickable { expanded = !expanded })

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
                            createEntry(entry)

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