package com.bursur.spiragps.preferences

expect fun getDarkModePreference(): Int
expect fun getTextSizePreference(): Int
expect fun getDyslexicModePreference(): Int
expect fun getKhaegarModePreference(): Int
expect fun getAnimationsEnabledPreference(): Int

expect fun setDarkModePreference(enabled: Int)
expect fun setTextSizePreference(size: Int)
expect fun setDyslexicModePreference(enabled: Int)
expect fun setKhaegarModePreference(enabled: Int)
expect fun setAnimationsEnabledPreference(enabled: Int)