package com.easysoft.sudoku.domain.repository

import com.easysoft.sudoku.core.qqwing.GameDifficulty
import com.easysoft.sudoku.core.qqwing.GameType
import com.easysoft.sudoku.data.database.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun get(uid: Long): Record
    fun getAll(): Flow<List<Record>>
    fun getAllSortByTime(): Flow<List<Record>>
    fun getAll(difficulty: GameDifficulty, type: GameType): Flow<List<Record>>
    suspend fun insert(record: Record)
    suspend fun insert(records: List<Record>)
    suspend fun delete(record: Record)
}