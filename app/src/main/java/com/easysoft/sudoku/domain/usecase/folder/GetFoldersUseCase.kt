package com.easysoft.sudoku.domain.usecase.folder

import com.easysoft.sudoku.data.database.model.Folder
import com.easysoft.sudoku.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoldersUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    operator fun invoke(): Flow<List<Folder>> = folderRepository.getAll()
}