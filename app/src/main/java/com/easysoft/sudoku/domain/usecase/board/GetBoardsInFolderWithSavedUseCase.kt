package com.easysoft.sudoku.domain.usecase.board

import com.easysoft.sudoku.domain.repository.BoardRepository
import javax.inject.Inject

class GetBoardsInFolderWithSavedUseCase @Inject constructor(
    private val boardRepository: BoardRepository
){
    operator fun invoke(folderUid: Long) = boardRepository.getInFolderWithSaved(folderUid)
}