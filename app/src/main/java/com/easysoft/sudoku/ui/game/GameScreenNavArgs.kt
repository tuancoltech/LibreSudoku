package com.easysoft.sudoku.ui.game

data class GameScreenNavArgs(
    val gameUid: Long,
    val playedBefore: Boolean = false
)
