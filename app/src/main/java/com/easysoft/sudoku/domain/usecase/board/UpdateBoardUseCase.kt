package com.easysoft.sudoku.domain.usecase.board

import com.easysoft.sudoku.data.database.model.SudokuBoard
import com.easysoft.sudoku.domain.repository.BoardRepository
import javax.inject.Inject

class UpdateBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(board: SudokuBoard) = boardRepository.update(board)
}