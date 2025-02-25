package com.bursur.spiragps.preferences

external fun loadDarkModePreference(): Int
external fun loadTextSizePreference(): Int
external fun loadDyslexicModePreference(): Int
external fun loadKhaegarModePreference(): Int

external fun saveDarkModePreference(enabled: Int)
external fun saveTextSizePreference(size: Int)
external fun saveDyslexicModePreference(enabled: Int)
external fun saveKhaegarModePreference(enabled: Int)

actual fun getDarkModePreference(): Int = loadDarkModePreference()
actual fun getTextSizePreference(): Int = loadTextSizePreference()
actual fun getDyslexicModePreference(): Int = loadDyslexicModePreference()
actual fun getKhaegarModePreference(): Int = loadKhaegarModePreference()

actual fun setDarkModePreference(enabled: Int) {
    saveDarkModePreference(enabled)
}

actual fun setTextSizePreference(size: Int) {
    saveTextSizePreference(size)
}

actual fun setDyslexicModePreference(enabled: Int) {
    saveDyslexicModePreference(enabled)
}

actual fun setKhaegarModePreference(enabled: Int) {
    saveKhaegarModePreference(enabled)
}