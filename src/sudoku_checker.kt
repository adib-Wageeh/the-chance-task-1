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
    check("Given a valid Sudoku board, When checking validity, Then it should return true", isValidSudoku(validBoard), true)
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
    check("Given a Sudoku board with a repeated number in the first row, When checking validity, Then it should return false", isValidSudoku(repeatedRowBoard), false)
    // endregion

    // region empty board
    val emptyBoard = Array(9) { Array(0) { "" } }
    check("Given a Sudoku empty board, When checking validity, Then it should return false", isValidSudoku(emptyBoard), false)
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
    check("Given a Sudoku board with a repeated number in the first column, When checking validity, Then it should return false", isValidSudoku(repeatedColumnBoard), false)
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
    check("Given a Sudoku board with a repeated number in a 3x3 subgrid, When checking validity, Then it should return false", isValidSudoku(repeatedSubgridBoard), false)
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
    check("Given a Sudoku board containing an invalid character, When checking validity, Then it should return false", isValidSudoku(invalidCharBoard), false)
    // endregion

    // region more columns than rows
    val moreColumnsBoard = arrayOf(
        arrayOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
        arrayOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
        arrayOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-")
    )
    check("Given a Sudoku board with more columns than rows, When checking validity, Then it should return false", isValidSudoku(moreColumnsBoard), false)
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
    check("Given a Sudoku board with more rows than columns, When checking validity, Then it should return false", isValidSudoku(moreRowsBoard), false)
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
    check("Given a Sudoku board with different Rows Or Columns, When checking validity, Then it should return false", isValidSudoku(differentRowsOrColumnsBoard), false)
    // endregion
}

fun isValidSudoku(board: Array<Array<String>>): Boolean {

    // if board is empty or rows and columns size misMatch
    if (board.isEmpty() || board.any { it.size != board.size }) return false
    // if subGrid size is not a perfect square of board size
    if (!isPerfectSquare(board.size)) return false

    val boardSize = board.size
    val subgridSize = sqrt(boardSize.toDouble()).toInt()
    // to track items in every column (to check for duplicates in columns)
    val columnsMap = mutableMapOf<Int, MutableSet<String>>()
    // each key represents subGrid location in the board ,and it's value is items in it.
    val subGridsMap = mutableMapOf<Pair<Int, Int>, MutableSet<String>>()

    board.forEachIndexed { rowIndex, row ->
        val rowSet = mutableSetOf<String>()

        row.forEachIndexed rowLoop@ { colIndex, value ->
            if (value == "-") return@rowLoop // skip "-"

            val item : Int = value.toIntOrNull() ?: return false // wrong item in the board
            if(item < 1 || item > boardSize) return false // number outside the boundaries
            if (!rowSet.add(value)) return false // for duplicates in same row
            val colSet = columnsMap.getOrPut(colIndex) { mutableSetOf() }
            if (!colSet.add(value)) return false // for duplicates in same column

            val subgridKey = Pair(rowIndex / subgridSize, colIndex / subgridSize)
            val subgridSet = subGridsMap.getOrPut(subgridKey) { mutableSetOf() }
            if (!subgridSet.add(value)) return false // duplicate in same subgrid
        }
    }

    return true
}

fun isPerfectSquare(n: Int): Boolean {
    val root = sqrt(n.toDouble()).toInt()
    return root * root == n
}