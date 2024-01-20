package spiragps.data.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
interface ContentsState {
    var selectedChapter: String
}

private class ContentsStateImpl(selectedChapter: String) : ContentsState {
    override var selectedChapter: String by mutableStateOf(selectedChapter)

    companion object {
        val saver = Saver<ContentsStateImpl, List<Any>>(
            save = { listOf(it.selectedChapter) },
            restore = {
                ContentsStateImpl(it[0] as String)
            }
        )
    }
}

@Composable
fun rememberContentsState(): ContentsState = rememberSaveable(
    saver = ContentsStateImpl.saver
) {
    ContentsStateImpl("")
}