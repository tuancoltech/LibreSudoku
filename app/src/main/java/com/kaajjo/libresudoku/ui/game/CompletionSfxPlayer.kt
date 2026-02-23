package com.kaajjo.libresudoku.ui.game

import androidx.compose.runtime.Composable

interface CompletionSfxPlayer {
    fun play()
    fun release()
}

@Composable
fun rememberCompletionSfxPlayer(): CompletionSfxPlayer {
    return rememberAndroidCompletionSfxPlayer()
}
