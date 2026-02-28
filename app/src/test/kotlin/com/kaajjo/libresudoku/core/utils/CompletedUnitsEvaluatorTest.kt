package com.kaajjo.libresudoku.core.utils

import com.kaajjo.libresudoku.core.Cell
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CompletedUnitsEvaluatorTest {

    @Test
    fun newlyCompletedUnits_9x9SingleMove_CompletesRowColumnAndBox() {
        val size = 9
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also { it[0][0].value = 0 }
        val current = copyBoard(solved)

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val newUnits = currentUnits - previousUnits
        val newCells = completedCellsForUnits(
            units = newUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        assertEquals(setOf(0), newUnits.rows)
        assertEquals(setOf(0), newUnits.columns)
        assertEquals(setOf(0), newUnits.boxes)
        assertEquals(21, newCells.size)
        assertTrue(newCells.contains(0 to 0))
        assertTrue(newCells.contains(0 to 8))
        assertTrue(newCells.contains(8 to 0))
        assertTrue(newCells.contains(2 to 2))
    }

    @Test
    fun newlyCompletedUnits_6x6SingleMove_Uses2x3BoxLayout() {
        val size = 6
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also { it[1][2].value = 0 }
        val current = copyBoard(solved)

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 2,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 2,
            sectionWidth = 3
        )

        val newUnits = currentUnits - previousUnits
        val newCells = completedCellsForUnits(
            units = newUnits,
            size = size,
            sectionHeight = 2,
            sectionWidth = 3
        )

        assertEquals(setOf(1), newUnits.rows)
        assertEquals(setOf(2), newUnits.columns)
        assertEquals(setOf(0), newUnits.boxes)
        assertEquals(13, newCells.size)
        assertTrue(newCells.contains(1 to 2))
        assertTrue(newCells.contains(0 to 0))
        assertTrue(newCells.contains(5 to 2))
    }

    @Test
    fun completionFxCellsForTransition_whenFinalBoardCompleted_returnsAllCells_9x9() {
        val size = 9
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also { it[0][0].value = 0 }
        val current = copyBoard(solved)

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val fxCells = completionFxCellsForTransition(
            previousUnits = previousUnits,
            currentUnits = currentUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        assertEquals(size * size, fxCells.size)
        assertTrue(fxCells.contains(0 to 0))
        assertTrue(fxCells.contains(8 to 8))
    }

    @Test
    fun completionFxCellsForTransition_whenFinalBoardCompleted_returnsAllCells_6x6() {
        val size = 6
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also { it[5][5].value = 0 }
        val current = copyBoard(solved)

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 2,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 2,
            sectionWidth = 3
        )

        val fxCells = completionFxCellsForTransition(
            previousUnits = previousUnits,
            currentUnits = currentUnits,
            size = size,
            sectionHeight = 2,
            sectionWidth = 3
        )

        assertEquals(size * size, fxCells.size)
        assertTrue(fxCells.contains(0 to 0))
        assertTrue(fxCells.contains(5 to 5))
    }

    @Test
    fun completionFxCellsForTransition_whenNotFinalBoardCompleted_returnsOnlyNewUnitsCells() {
        val size = 9
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also { it[0][0].value = 0 }
        val current = copyBoard(solved).also { it[8][8].value = 0 }

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val fxCells = completionFxCellsForTransition(
            previousUnits = previousUnits,
            currentUnits = currentUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val expectedNewlyCompletedUnits = currentUnits - previousUnits
        val expectedCells = completedCellsForUnits(
            units = expectedNewlyCompletedUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        assertEquals(expectedCells, fxCells)
        assertTrue(fxCells.isNotEmpty())
        assertTrue(fxCells.size < size * size)
    }

    @Test
    fun completionFxCellsForTransition_whenNotFinalBoardCompleted_includesAllNewUnitCells() {
        val size = 9
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also { it[0][0].value = 0 }
        val current = copyBoard(solved).also { it[8][8].value = 0 }

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val newlyCompletedCells = completedCellsForUnits(
            units = currentUnits - previousUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val fxCells = completionFxCellsForTransition(
            previousUnits = previousUnits,
            currentUnits = currentUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        assertEquals(newlyCompletedCells, fxCells)
    }

    @Test
    fun completionFxCellsForTransition_whenUnitsRecomplete_canAnimateAgain() {
        val size = 9
        val solved = createSolvedBoard(size)
        val previous = copyBoard(solved).also {
            it[0][0].value = 0
            it[8][8].value = 0
        }
        val current = copyBoard(solved).also { it[0][0].value = 0 }

        val previousUnits = computeCompletedUnits(
            board = previous,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )
        val currentUnits = computeCompletedUnits(
            board = current,
            solvedBoard = solved,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val newlyCompletedCells = completedCellsForUnits(
            units = currentUnits - previousUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        val fxCells = completionFxCellsForTransition(
            previousUnits = previousUnits,
            currentUnits = currentUnits,
            size = size,
            sectionHeight = 3,
            sectionWidth = 3
        )

        assertEquals(newlyCompletedCells, fxCells)
        assertTrue(fxCells.contains(8 to 8))
    }

    private fun createSolvedBoard(size: Int): List<List<Cell>> {
        return List(size) { row ->
            List(size) { col ->
                Cell(
                    row = row,
                    col = col,
                    value = (row * size) + col + 1
                )
            }
        }
    }

    private fun copyBoard(board: List<List<Cell>>): List<List<Cell>> {
        return board.map { row -> row.map { cell -> cell.copy() } }
    }
}
