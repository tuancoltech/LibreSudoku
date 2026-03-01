package com.easysoft.sudoku.data.database.repository

import com.easysoft.sudoku.data.database.AppDatabase
import com.easysoft.sudoku.domain.repository.DatabaseRepository
import kotlinx.coroutines.runBlocking

class DatabaseRepositoryImpl(
    private val appDatabase: AppDatabase
) : DatabaseRepository {
    /**
     * Completely resets database. Clearing all tables and primary key sequence
     */
    override suspend fun resetDb() {
        appDatabase.runInTransaction {
            runBlocking {
                appDatabase.clearAllTables()
                appDatabase.databaseDao().clearPrimaryKeyIndex()
            }
        }
    }
}