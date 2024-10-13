package com.bursur.spiragps.utils

import kotlinx.coroutines.delay

external fun openFile()
external fun writeFile(data: String)
external fun getLoadedData(): String

object FileService {
    suspend fun loadFile(): String {
        openFile()

        var data = getLoadedData()
        while (data == "#DEADFACE") {
            delay(100)
            data = getLoadedData()
        }

        return data
    }

    fun saveFile(data: String) {
        writeFile(data)
    }
}