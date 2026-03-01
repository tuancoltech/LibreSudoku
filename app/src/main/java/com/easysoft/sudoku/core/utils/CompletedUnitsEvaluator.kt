package com.easysoft.sudoku.core.utils

import com.easysoft.sudoku.core.Cell

data class CompletedUnits(
    val rows: Set<Int> = emptySet(),
    val columns: Set<Int> = emptySet(),
    val boxes: Set<Int> = emptySet()
) {
    fun isEmpty(): Boolean = rows.isEmpty() && columns.isEmpty() && boxes.isEmpty()

    fun isFullyCompleted(
        size: Int,
        sectionHeight: Int,
        sectionWidth: Int
    ): Boolean {
        val boxesCount = (size / sectionHeight) * (size / sectionWidth)
        return rows.size == size && columns.size == size && boxes.size == boxesCount
    }

    operator fun minus(previous: CompletedUnits): CompletedUnits {
        return CompletedUnits(
            rows = rows - previous.rows,
            columns = columns - previous.columns,
            boxes = boxes - previous.boxes
        )
    }
}

fun computeCompletedUnits(
    board: List<List<Cell>>,
    solvedBoard: List<List<Cell>>,
    sectionHeight: Int,
    sectionWidth: Int
): CompletedUnits {
    if (board.isEmpty() || solvedBoard.isEmpty()) return CompletedUnits()

    val size = board.size
    val completedRows = mutableSetOf<Int>()
    val completedColumns = mutableSetOf<Int>()
    val completedBoxes = mutableSetOf<Int>()

    for (row in 0 until size) {
        val rowCompleted = (0 until size).all { col ->
            board[row][col].value != 0 && board[row][col].value == solvedBoard[row][col].value
        }
        if (rowCompleted) {
            completedRows.add(row)
        }
    }

    for (col in 0 until size) {
        val columnCompleted = (0 until size).all { row ->
            board[row][col].value != 0 && board[row][col].value == solvedBoard[row][col].value
        }
        if (columnCompleted) {
            completedColumns.add(col)
        }
    }

    val sectionsPerRow = size / sectionWidth
    for (startRow in 0 until size step sectionHeight) {
        for (startCol in 0 until size step sectionWidth) {
            var boxCompleted = true
            for (row in startRow until startRow + sectionHeight) {
                for (col in startCol until startCol + sectionWidth) {
                    if (board[row][col].value == 0 || board[row][col].value != solvedBoard[row][col].value) {
                        boxCompleted = false
                    }
                }
            }
            if (boxCompleted) {
                val boxIndex = (startRow / sectionHeight) * sectionsPerRow + (startCol / sectionWidth)
                completedBoxes.add(boxIndex)
            }
        }
    }

    return CompletedUnits(
        rows = completedRows,
        columns = completedColumns,
        boxes = completedBoxes
    )
}

fun completedCellsForUnits(
    units: CompletedUnits,
    size: Int,
    sectionHeight: Int,
    sectionWidth: Int
): Set<Pair<Int, Int>> {
    val cells = mutableSetOf<Pair<Int, Int>>()

    units.rows.forEach { row ->
        for (col in 0 until size) {
            cells.add(row to col)
        }
    }

    units.columns.forEach { col ->
        for (row in 0 until size) {
            cells.add(row to col)
        }
    }

    val sectionsPerRow = size / sectionWidth
    units.boxes.forEach { box ->
        val startRow = (box / sectionsPerRow) * sectionHeight
        val startCol = (box % sectionsPerRow) * sectionWidth
        for (row in startRow until startRow + sectionHeight) {
            for (col in startCol until startCol + sectionWidth) {
                cells.add(row to col)
            }
        }
    }

    return cells
}

fun completionFxCellsForTransition(
    previousUnits: CompletedUnits,
    currentUnits: CompletedUnits,
    size: Int,
    sectionHeight: Int,
    sectionWidth: Int
): Set<Pair<Int, Int>> {
    if (currentUnits.isFullyCompleted(size, sectionHeight, sectionWidth)) {
        return allBoardCells(size)
    }

    val newlyCompletedUnits = currentUnits - previousUnits
    if (newlyCompletedUnits.isEmpty()) {
        return emptySet()
    }

    return completedCellsForUnits(
        units = newlyCompletedUnits,
        size = size,
        sectionHeight = sectionHeight,
        sectionWidth = sectionWidth
    )
}

private fun allBoardCells(size: Int): Set<Pair<Int, Int>> {
    val cells = mutableSetOf<Pair<Int, Int>>()
    for (row in 0 until size) {
        for (col in 0 until size) {
            cells.add(row to col)
        }
    }
    return cells
}
