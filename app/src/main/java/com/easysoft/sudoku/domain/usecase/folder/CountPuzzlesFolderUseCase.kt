package com.easysoft.sudoku.domain.usecase.folder

import com.easysoft.sudoku.domain.repository.FolderRepository
import javax.inject.Inject

class CountPuzzlesFolderUseCase @Inject constructor(
    private val folderRepository: FolderRepository
) {
    operator fun invoke(uid: Long) = folderRepository.countPuzzlesFolder(uid)
}