package com.bursur.spiragps.route.contents

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.backbutton.BackButton
import com.bursur.spiragps.navigation.NavigationState
import com.bursur.spiragps.route.data.Chapter
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ContentsView(modifier: Modifier = Modifier, chapters: ArrayList<Chapter>, contentsState: ContentsState, navigationState: NavigationState) {
    val textColour by animateColorAsState(SpiraGPSColours.text)

    Column(
        modifier = modifier.padding(10.dp).width(150.dp)
    ) {

        // Back Button
        BackButton(navigationState = navigationState)

        // Title
        Text(
            text = "Chapters:",
            style = SpiraGPSText.typography.contentsTitle,
            color = textColour,
            modifier = Modifier.wrapContentSize()
        )

        LazyColumn {
            itemsIndexed(items = chapters) { index, chapter ->
                if(chapter.index == -1)
                    chapter.index = index

                Text(
                    text = chapter.title,
                    style = SpiraGPSText.typography.contentsEntry,
                    color = textColour,
                    modifier = Modifier
                        .wrapContentSize()
                        .width(150.dp)
                        .padding(vertical = 5.dp)
                        .clickable {
                            contentsState.selectedChapter = chapter.index
                        }
                )
            }
        }
    }
}