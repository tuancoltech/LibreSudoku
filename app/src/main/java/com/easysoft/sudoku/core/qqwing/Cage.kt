package com.easysoft.sudoku.core.qqwing

import com.easysoft.sudoku.core.Cell
import kotlinx.serialization.Serializable

@Serializable
data class Cage(
    val id: Int = 0,
    val sum: Int = 0,
    val cells: List<Cell> = emptyList()
) {
    fun size() = cells.size
}