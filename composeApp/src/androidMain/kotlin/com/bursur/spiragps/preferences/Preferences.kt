package com.bursur.spiragps.preferences

private var textSize: Int = 1
private var darkMode: Int = 0
private var dyslexicMode: Int = 0
private var khaegarMode: Int = 0
private var animationsEnabled: Int = 1

actual fun getDarkModePreference(): Int = darkMode
actual fun getTextSizePreference(): Int = textSize
actual fun getDyslexicModePreference(): Int = dyslexicMode
actual fun getKhaegarModePreference(): Int = khaegarMode
actual fun getAnimationsEnabledPreference(): Int = animationsEnabled

actual fun setDarkModePreference(enabled: Int) {
    darkMode = enabled
}

actual fun setTextSizePreference(size: Int) {
    textSize = size
}

actual fun setDyslexicModePreference(enabled: Int) {
    dyslexicMode = enabled
}

actual fun setKhaegarModePreference(enabled: Int) {
    khaegarMode = enabled
}

actual fun setAnimationsEnabledPreference(enabled: Int) {
    animationsEnabled = enabled
}
