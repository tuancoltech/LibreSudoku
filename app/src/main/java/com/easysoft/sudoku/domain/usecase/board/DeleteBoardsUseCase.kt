package com.easysoft.sudoku.domain.usecase.board

import com.easysoft.sudoku.data.database.model.SudokuBoard
import com.easysoft.sudoku.domain.repository.BoardRepository
import javax.inject.Inject

class DeleteBoardsUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(boards: List<SudokuBoard>) = boardRepository.delete(boards)
}