package com.easysoft.sudoku.domain.usecase.board

import com.easysoft.sudoku.data.database.model.SudokuBoard
import com.easysoft.sudoku.domain.repository.BoardRepository
import javax.inject.Inject

class DeleteBoardUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(board: SudokuBoard) = boardRepository.delete(board)
}