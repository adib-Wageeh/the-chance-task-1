import kotlin.math.sqrt

fun main() {
    // region valid sudoku
    val validBoard = arrayOf(
        arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        arrayOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        arrayOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )
    check("Given a valid Sudoku board, When checking validity, Then it should return true", checkValidSudoku(validBoard), true)
    // endregion

    // region valid 4x4 sudoku
    val validSecondBoard = arrayOf(
        arrayOf("1", "2", "3", "4"),
        arrayOf("3", "4", "1", "2"),
        arrayOf("2", "1", "4", "3"),
        arrayOf("4", "3", "2", "1")
    )
    check("Given a valid 4x4 Sudoku board, When checking validity, Then it should return true", checkValidSudoku(validSecondBoard), true)
    // endregion

    // region repeated number in row
    val repeatedRowBoard = arrayOf(
        arrayOf("5", "3", "3", "-", "7", "-", "-", "-", "-"),
        arrayOf("6", "-", "-", "1", "9", "5", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        arrayOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )
    check("Given a Sudoku board with a repeated number in the first row, When checking validity, Then it should return false", checkValidSudoku(repeatedRowBoard), false)
    // endregion

    // region empty board
    val emptyBoard = Array(9) { Array(0) { "" } }
    check("Given a Sudoku empty board, When checking validity, Then it should return false", checkValidSudoku(emptyBoard), false)
    // endregion

    // region repeated number in column
    val repeatedColumnBoard = arrayOf(
        arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        arrayOf("5", "-", "-", "1", "9", "5", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        arrayOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )
    check("Given a Sudoku board with a repeated number in the first column, When checking validity, Then it should return false", checkValidSudoku(repeatedColumnBoard), false)
    // endregion

    // region repeated number in subgrid
    val repeatedSubgridBoard = arrayOf(
        arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-"),
        arrayOf("6", "-", "5", "1", "9", "5", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        arrayOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )
    check("Given a Sudoku board with a repeated number in a 3x3 subgrid, When checking validity, Then it should return false", checkValidSudoku(repeatedSubgridBoard), false)
    // endregion

    // region invalid character
    val invalidCharBoard = arrayOf(
        arrayOf("a", "-", "3", "-", "7", "-", "-", "-", "-"),
        arrayOf("-", "-", "1", "1", "9", "5", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-"),
        arrayOf("8", "-", "-", "-", "6", "-", "-", "-", "3"),
        arrayOf("4", "-", "-", "8", "-", "3", "-", "-", "1"),
        arrayOf("7", "-", "-", "-", "2", "-", "-", "-", "6"),
        arrayOf("-", "6", "-", "-", "-", "-", "2", "8", "-"),
        arrayOf("-", "-", "-", "4", "1", "9", "-", "-", "5"),
        arrayOf("-", "-", "-", "-", "8", "-", "-", "7", "9")
    )
    check("Given a Sudoku board containing an invalid character, When checking validity, Then it should return false", checkValidSudoku(invalidCharBoard), false)
    // endregion

    // region more columns than rows
    val moreColumnsBoard = arrayOf(
        arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
        arrayOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-")
    )
    check("Given a Sudoku board with more columns than rows, When checking validity, Then it should return false", checkValidSudoku(moreColumnsBoard), false)
    // endregion

    // region more rows than columns
    val moreRowsBoard = arrayOf(
        arrayOf("5", "3", "-"),
        arrayOf("6", "-", "1"),
        arrayOf("-", "9", "8"),
        arrayOf("8", "-", "-"),
        arrayOf("4", "-", "-"),
        arrayOf("7", "-", "-"),
        arrayOf("-", "6", "-"),
        arrayOf("-", "-", "-"),
        arrayOf("-", "-", "-"),
        arrayOf("-", "-", "-")
    )
    check("Given a Sudoku board with more rows than columns, When checking validity, Then it should return false", checkValidSudoku(moreRowsBoard), false)
    // endregion

    // region inconsistent row lengths
    val differentRowsOrColumnsBoard = arrayOf(
        arrayOf("5", "3", "-", "6"),
        arrayOf("6", "-", "1"),
        arrayOf("-", "9", "8"),
        arrayOf("8", "-", "-"),
        arrayOf("4", "-", "-"),
        arrayOf("7", "-", "-"),
        arrayOf("-", "6", "-"),
        arrayOf("-", "-", "-"),
        arrayOf("-", "-"),
        arrayOf("-", "-", "-")
    )
    check("Given a Sudoku board with different Rows Or Columns, When checking validity, Then it should return false", checkValidSudoku(differentRowsOrColumnsBoard), false)
    // endregion
}

fun checkValidSudoku(board: Array<Array<String>>): Boolean {

    if (board.isEmpty() || board.any { it.size != board.size }) return false
    if (!isPerfectSquare(board.size)) return false

    val boardSize = board.size
    val subgridSize = sqrt(boardSize.toDouble()).toInt()
    val columnsMap = mutableMapOf<Int, MutableSet<String>>()
    val subGridsMap = mutableMapOf<Pair<Int, Int>, MutableSet<String>>()

    board.forEachIndexed { rowIndex, row ->
        val rowSet = mutableSetOf<String>()

        row.forEachIndexed rowLoop@ { colIndex, value ->
            if (value == "-") return@rowLoop

            val item : Int = value.toIntOrNull() ?: return false
            if(item < 1 || item > boardSize) return false
            if (!rowSet.add(value)) return false
            val colSet = columnsMap.getOrPut(colIndex) { mutableSetOf() }
            if (!colSet.add(value)) return false

            val subgridKey = Pair(rowIndex / subgridSize, colIndex / subgridSize)
            val subgridSet = subGridsMap.getOrPut(subgridKey) { mutableSetOf() }
            if (!subgridSet.add(value)) return false
        }
    }

    return true
}

fun isPerfectSquare(n: Int): Boolean {
    val root = sqrt(n.toDouble()).toInt()
    return root * root == n
}