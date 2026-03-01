package com.easysoft.sudoku.ui.settings.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easysoft.sudoku.data.database.AppDatabase
import com.easysoft.sudoku.data.datastore.AppSettingsManager
import com.easysoft.sudoku.data.datastore.TipCardsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsOtherViewModel @Inject constructor(
    private val settings: AppSettingsManager,
    private val tipCardsDataStore: TipCardsDataStore,
    private val appDatabase: AppDatabase
) : ViewModel() {
    val saveLastSelectedDifficultyType = settings.saveSelectedGameDifficultyType
    fun updateSaveLastSelectedDifficultyType(enabled: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            settings.setSaveSelectedGameDifficultyType(enabled)
        }

    val keepScreenOn = settings.keepScreenOn
    fun updateKeepScreenOn(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settings.setKeepScreenOn(enabled)
        }
    }

    fun resetTipCards() {
        viewModelScope.launch {
            tipCardsDataStore.setStreakCard(true)
            tipCardsDataStore.setRecordCard(true)
        }
    }

    fun deleteAllTables() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.clearAllTables()
        }
    }
}
