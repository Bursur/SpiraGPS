package spiragps.data

import kotlinx.coroutines.delay

external fun getClipboard(): String
external fun loadClipboardData()

object ClipboardService {
    suspend fun getData(): String {
        loadClipboardData()

        var data = getClipboard()
        while (data == "#DEADFACE") {
            delay(100)
            data = getClipboard()
        }

        println(data)

        return data
    }
}