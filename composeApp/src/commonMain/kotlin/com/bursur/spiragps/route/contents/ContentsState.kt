package com.bursur.spiragps.route.contents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
interface ContentsState {
    var selectedChapter: Int
}

private class ContentsStateImpl(selectedChapter: Int) : ContentsState {
    override var selectedChapter: Int by mutableStateOf(selectedChapter)

    companion object {
        val saver = Saver<ContentsStateImpl, List<Any>>(
            save = { listOf(it.selectedChapter) },
            restore = {
                ContentsStateImpl(it[0] as Int)
            }
        )
    }
}

@Composable
fun rememberContentsState(): ContentsState = rememberSaveable(
    saver = ContentsStateImpl.saver
) {
    ContentsStateImpl(-1)
}