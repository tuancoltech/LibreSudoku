package com.easysoft.sudoku.domain.usecase.record

import com.easysoft.sudoku.core.qqwing.GameDifficulty
import com.easysoft.sudoku.core.qqwing.GameType
import com.easysoft.sudoku.domain.repository.RecordRepository
import javax.inject.Inject

class GetAllRecordsUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(difficulty: GameDifficulty, type: GameType) = recordRepository.getAll(difficulty, type)
}