package com.bursur.spiragps.platform

actual fun isApp(): Boolean = false
actual fun isWebSite(): Boolean = !isApp()
actual fun useDismissableContents(): Boolean = false
actual fun useDismissableConditions(): Boolean = false