package com.easysoft.sudoku.util

import com.easysoft.sudoku.BuildConfig

object FlavorUtil {
    fun isFoss(): Boolean  = BuildConfig.FLAVOR == "foss"
}