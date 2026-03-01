package com.easysoft.sudoku.domain.usecase

import com.easysoft.sudoku.data.database.model.SudokuBoard
import com.easysoft.sudoku.domain.repository.BoardRepository
import javax.inject.Inject

class UpdateManyBoardsUseCase @Inject constructor(
    private val boardRepository: BoardRepository
) {
    suspend operator fun invoke(boards: List<SudokuBoard>) = boardRepository.update(boards)
}