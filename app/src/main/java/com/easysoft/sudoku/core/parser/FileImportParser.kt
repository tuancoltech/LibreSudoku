package com.easysoft.sudoku.core.parser

interface FileImportParser {
    fun toBoards(content: String): Pair<Boolean, List<String>>
}