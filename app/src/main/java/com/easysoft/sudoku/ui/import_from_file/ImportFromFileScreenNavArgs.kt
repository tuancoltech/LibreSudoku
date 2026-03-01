package com.easysoft.sudoku.ui.import_from_file

data class ImportFromFileScreenNavArgs(
    val fileUri: String?,
    val folderUid: Long = -1,
    val fromDeepLink: Boolean = false
)
