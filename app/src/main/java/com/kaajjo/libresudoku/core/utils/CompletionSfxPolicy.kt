package com.kaajjo.libresudoku.core.utils

object CompletionSfxPolicy {
    const val RINGER_MODE_SILENT = 0
    const val RINGER_MODE_VIBRATE = 1
    const val RINGER_MODE_NORMAL = 2

    fun shouldPlay(ringerMode: Int, notificationVolume: Int): Boolean {
        return ringerMode == RINGER_MODE_NORMAL && notificationVolume > 0
    }
}
