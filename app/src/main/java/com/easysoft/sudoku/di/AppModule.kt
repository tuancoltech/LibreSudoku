package com.easysoft.sudoku.di

import android.app.Application
import android.content.Context
import com.easysoft.sudoku.data.database.AppDatabase
import com.easysoft.sudoku.data.database.dao.BoardDao
import com.easysoft.sudoku.data.database.dao.FolderDao
import com.easysoft.sudoku.data.database.dao.RecordDao
import com.easysoft.sudoku.data.database.dao.SavedGameDao
import com.easysoft.sudoku.data.database.repository.BoardRepositoryImpl
import com.easysoft.sudoku.data.database.repository.DatabaseRepositoryImpl
import com.easysoft.sudoku.data.database.repository.FolderRepositoryImpl
import com.easysoft.sudoku.data.database.repository.RecordRepositoryImpl
import com.easysoft.sudoku.data.database.repository.SavedGameRepositoryImpl
import com.easysoft.sudoku.data.datastore.AppSettingsManager
import com.easysoft.sudoku.data.datastore.ThemeSettingsManager
import com.easysoft.sudoku.domain.repository.BoardRepository
import com.easysoft.sudoku.domain.repository.DatabaseRepository
import com.easysoft.sudoku.domain.repository.FolderRepository
import com.easysoft.sudoku.domain.repository.RecordRepository
import com.easysoft.sudoku.domain.repository.SavedGameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(appDatabase: AppDatabase): DatabaseRepository
        = DatabaseRepositoryImpl(appDatabase)

    @Provides
    @Singleton
    fun provideFolderRepository(folderDao: FolderDao): FolderRepository
        = FolderRepositoryImpl(folderDao)

    @Provides
    @Singleton
    fun provideFolderDao(appDatabase: AppDatabase): FolderDao = appDatabase.folderDao()

    @Singleton
    @Provides
    fun provideRecordRepository(recordDao: RecordDao): RecordRepository =
        RecordRepositoryImpl(recordDao)

    @Singleton
    @Provides
    fun provideRecordDao(appDatabase: AppDatabase): RecordDao = appDatabase.recordDao()

    @Singleton
    @Provides
    fun provideBoardRepository(boardDao: BoardDao): BoardRepository = BoardRepositoryImpl(boardDao)

    @Singleton
    @Provides
    fun provideBoardDao(appDatabase: AppDatabase): BoardDao = appDatabase.boardDao()


    @Singleton
    @Provides
    fun provideSavedGameRepository(savedGameDao: SavedGameDao): SavedGameRepository =
        SavedGameRepositoryImpl(savedGameDao)

    @Singleton
    @Provides
    fun provideSavedGameDao(appDatabase: AppDatabase): SavedGameDao = appDatabase.savedGameDao()


    @Provides
    @Singleton
    fun provideAppSettingsManager(@ApplicationContext context: Context) =
        AppSettingsManager(context)

    @Provides
    @Singleton
    fun provideThemeSettingsManager(@ApplicationContext context: Context) =
        ThemeSettingsManager(context)

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase = AppDatabase.getInstance(context = app)
}
