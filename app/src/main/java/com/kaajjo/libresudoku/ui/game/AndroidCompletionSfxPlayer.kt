package com.kaajjo.libresudoku.ui.game

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.kaajjo.libresudoku.R
import com.kaajjo.libresudoku.core.utils.CompletionSfxPolicy

private class AndroidCompletionSfxPlayer(context: android.content.Context) : CompletionSfxPlayer {
    private val audioManager = context.getSystemService(AudioManager::class.java)
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(1)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()
    private val soundId = soundPool.load(context, R.raw.completion_coin_sparkle, 1)
    private var isLoaded = false

    init {
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0 && sampleId == soundId) {
                isLoaded = true
            }
        }
    }

    override fun play() {
        if (!isLoaded) return
        if (!CompletionSfxPolicy.shouldPlay(
                ringerMode = audioManager.ringerMode,
                notificationVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)
            )
        ) {
            return
        }

        soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
    }

    override fun release() {
        soundPool.release()
    }
}

@Composable
internal fun rememberAndroidCompletionSfxPlayer(): CompletionSfxPlayer {
    val context = LocalContext.current
    return remember(context) { AndroidCompletionSfxPlayer(context.applicationContext) }
}
