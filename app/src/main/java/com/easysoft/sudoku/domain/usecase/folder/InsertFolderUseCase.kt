package com.easysoft.sudoku.domain.usecase.folder

import com.easysoft.sudoku.data.database.model.Folder
import com.easysoft.sudoku.domain.repository.FolderRepository
import javax.inject.Inject

class InsertFolderUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    suspend operator fun invoke(folder: Folder) = folderRepository.insert(folder)
}