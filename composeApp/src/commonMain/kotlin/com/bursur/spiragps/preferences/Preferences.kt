package com.bursur.spiragps.preferences

expect fun getDarkModePreference(): Int
expect fun getTextSizePreference(): Int
expect fun getDyslexicModePreference(): Int

expect fun setDarkModePreference(enabled: Int)
expect fun setTextSizePreference(size: Int)
expect fun setDyslexicModePreference(enabled: Int)