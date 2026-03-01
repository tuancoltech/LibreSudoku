package com.easysoft.sudoku.domain.repository

interface DatabaseRepository {
    suspend fun resetDb()
}