package com.easysoft.sudoku.data.database.repository

import com.easysoft.sudoku.core.qqwing.GameDifficulty
import com.easysoft.sudoku.core.qqwing.GameType
import com.easysoft.sudoku.data.database.dao.RecordDao
import com.easysoft.sudoku.data.database.model.Record
import com.easysoft.sudoku.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(
    private val recordDao: RecordDao
) : RecordRepository {
    override suspend fun get(uid: Long): Record = recordDao.get(uid)
    override fun getAll(): Flow<List<Record>> = recordDao.getAll()
    override fun getAllSortByTime(): Flow<List<Record>> = recordDao.getAllSortByTime()
    override fun getAll(difficulty: GameDifficulty, type: GameType) = recordDao.getAll(difficulty, type)
    override suspend fun insert(record: Record) = recordDao.insert(record)
    override suspend fun insert(records: List<Record>) = recordDao.insert(records)
    override suspend fun delete(record: Record) = recordDao.delete(record)
}