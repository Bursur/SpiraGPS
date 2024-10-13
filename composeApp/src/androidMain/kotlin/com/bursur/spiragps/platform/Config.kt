package com.bursur.spiragps.platform

actual fun isApp(): Boolean = true
actual fun isWebSite(): Boolean = !isApp()
actual fun useDismissableContents(): Boolean = true
actual fun useDismissableConditions(): Boolean = true