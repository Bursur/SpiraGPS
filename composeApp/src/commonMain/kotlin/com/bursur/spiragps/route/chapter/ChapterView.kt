package com.bursur.spiragps.route.chapter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.platform.isWebSite
import com.bursur.spiragps.route.conditions.ConditionState
import com.bursur.spiragps.route.entries.createEntry
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.route.title.TitleView

@Composable
fun ChapterView(chapter: Chapter, conditionState: ConditionState, titlePositionCallback: (Int, Float) -> Unit = { _: Int, _: Float -> }) {
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth(if(isWebSite()) .75f else .95f)) {
        TitleView(title = chapter.title, isChapter = true, modifier = Modifier.clickable { expanded = !expanded }) { position: Float ->
            titlePositionCallback(chapter.index, position)
        }

        AnimatedVisibility(visible = expanded) {
            Column (modifier = Modifier.padding(vertical = 10.dp)) {
                chapter.entries.forEach { entry: Entry ->
                    // Changes in state can't be picked up on a map, so we use lastChange to force re-composition. Feels Hacky look for a better option
                    val changes = conditionState.lastChange

                    val showEntry = conditionState.areConditionsMet(entry.requirement)

                    AnimatedVisibility(visible = showEntry) {
                        Column {
                            createEntry(entry = entry)

                            if (entry.trailingBreak) {
                                Spacer(modifier = Modifier.size(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}