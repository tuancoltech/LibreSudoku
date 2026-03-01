package com.easysoft.sudoku.ui.gameshistory

import com.easysoft.sudoku.R

enum class GameStateFilter(val resName: Int) {
    All(R.string.filter_all),
    Completed(R.string.filter_completed),
    InProgress(R.string.filter_in_progress),
    NotStarted(R.string.filter_not_started)
}