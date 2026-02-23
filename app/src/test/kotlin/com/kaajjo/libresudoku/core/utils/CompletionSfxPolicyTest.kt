package com.kaajjo.libresudoku.core.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CompletionSfxPolicyTest {

    @Test
    fun shouldPlay_returnsTrue_onlyWhenRingerNormalAndVolumePositive() {
        assertTrue(
            CompletionSfxPolicy.shouldPlay(
                ringerMode = CompletionSfxPolicy.RINGER_MODE_NORMAL,
                notificationVolume = 5
            )
        )
    }

    @Test
    fun shouldPlay_returnsFalse_whenSilent() {
        assertFalse(
            CompletionSfxPolicy.shouldPlay(
                ringerMode = CompletionSfxPolicy.RINGER_MODE_SILENT,
                notificationVolume = 5
            )
        )
    }

    @Test
    fun shouldPlay_returnsFalse_whenVibrate() {
        assertFalse(
            CompletionSfxPolicy.shouldPlay(
                ringerMode = CompletionSfxPolicy.RINGER_MODE_VIBRATE,
                notificationVolume = 5
            )
        )
    }

    @Test
    fun shouldPlay_returnsFalse_whenVolumeZero() {
        assertFalse(
            CompletionSfxPolicy.shouldPlay(
                ringerMode = CompletionSfxPolicy.RINGER_MODE_NORMAL,
                notificationVolume = 0
            )
        )
    }
}
