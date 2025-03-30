package com.bursur.spiragps.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
interface EditorState {
    var updateCounter: Int
    var chapterCount: Int
    var chapterName: Int
}

private class EditorStateImpl(updateCount: Int, chapterCount: Int, chapterName: Int) : EditorState {
    override var updateCounter: Int by mutableStateOf(updateCount)
    override var chapterCount: Int by mutableStateOf(chapterCount)
    override var chapterName: Int by mutableStateOf(chapterName)

    companion object {
        val saver = Saver<EditorStateImpl, List<Any>>(
            save = { listOf(it.updateCounter) },
            restore = {
                EditorStateImpl(it[0] as Int, it[1] as Int, it[2] as Int)
            }
        )
    }
}

@Composable
fun rememberEditorState(): EditorState = rememberSaveable(
    saver = EditorStateImpl.saver
) {
    EditorStateImpl(0, 0, 0)
}