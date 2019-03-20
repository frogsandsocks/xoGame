package xoGame

import java.lang.IllegalArgumentException
import kotlin.math.max


enum class XOGameSign {
    X, O, EMPTY
}


// Cell of game field
data class Cell(val row: Int, val column: Int)


class XOGameField(val size: Int) {

    private var field = mutableMapOf<Cell, XOGameSign>()

    init {

        if (size <= 0) throw IllegalArgumentException()

        for (row in 0 until size) {
            for (column in 0 until size)

                field[Cell(row, column)] = XOGameSign.EMPTY
        }
    }

    fun get(cell: Cell): XOGameSign = field[cell] ?: throw IllegalArgumentException()
    fun get(row: Int, column: Int): XOGameSign = get(Cell(row, column))

    fun set(cell: Cell, value: XOGameSign) {

        if (field[cell] == XOGameSign.EMPTY) field[cell] = value else throw IllegalArgumentException()
    }

    fun set(row: Int, column: Int, value: XOGameSign) = set(Cell(row, column), value)


    fun cellAddX(cell: Cell) = this.set(cell, XOGameSign.X)
    fun cellAddO(cell: Cell) = this.set(cell, XOGameSign.O)


    fun findLongestSequenceHorizontally(signRequested: XOGameSign): Int {

        var counter = 0
        var maxSequence = 0

        for (indexRow in 0 until size) {
            for (indexColumn in 0 until size) {

                if (get(Cell(indexRow, indexColumn)) == signRequested) counter++ else {

                    maxSequence = max(counter, maxSequence)
                    counter = 0
                }
            }

            maxSequence = max(counter, maxSequence)
            counter = 0
        }

        return maxSequence
    }


    fun findLongestSequenceVertically(signRequested: XOGameSign): Int {

        var counter = 0
        var maxSequence = 0

        for (indexColumn in 0 until size) {
            for (indexRow in 0 until size) {

                if (get(Cell(indexRow, indexColumn)) == signRequested) counter++ else {

                    maxSequence = max(counter, maxSequence)
                    counter = 0
                }
            }

            maxSequence = max(counter, maxSequence)
            counter = 0
        }

        return maxSequence
    }


    fun findLongestSequenceDiagonally(signRequested: XOGameSign): Int {

        var counterFirstHalfDiagonalRight = 0
        var counterSecondHalfDiagonalRight = 0
        var counterFirstHalfDiagonalLeft = 0
        var counterSecondHalfDiagonalLeft = 0
        var maxSequence = 0

        for (rowStartIndex in 0 until size) {

            for ((columnIndexDiagonalRight, rowIndex) in (rowStartIndex until size).withIndex()) {

                if (get(rowIndex, columnIndexDiagonalRight) == signRequested) counterFirstHalfDiagonalRight++ else {

                    maxSequence = max(maxSequence, counterFirstHalfDiagonalRight)
                    counterFirstHalfDiagonalRight = 0
                }


                if (get(columnIndexDiagonalRight, rowIndex) == signRequested) counterSecondHalfDiagonalRight++ else {

                    maxSequence = max(maxSequence, counterSecondHalfDiagonalRight)
                    counterSecondHalfDiagonalRight = 0
                }


                val columnIndexDiagonalLeft = size - 1 - columnIndexDiagonalRight


                if (get(rowIndex, columnIndexDiagonalLeft) == signRequested) counterFirstHalfDiagonalLeft++ else {

                    maxSequence = max(maxSequence, counterFirstHalfDiagonalLeft)
                    counterFirstHalfDiagonalLeft = 0
                }

                if (get(columnIndexDiagonalLeft, rowIndex) == signRequested) counterSecondHalfDiagonalLeft++ else {

                    maxSequence = max(maxSequence, counterSecondHalfDiagonalLeft)
                    counterSecondHalfDiagonalLeft = 0
                }

            }


            maxSequence = max(maxSequence, counterFirstHalfDiagonalRight)
            counterFirstHalfDiagonalRight = 0

            maxSequence = max(maxSequence, counterSecondHalfDiagonalRight)
            counterSecondHalfDiagonalRight = 0

            maxSequence = max(maxSequence, counterFirstHalfDiagonalLeft)
            counterFirstHalfDiagonalLeft = 0

            maxSequence = max(maxSequence, counterSecondHalfDiagonalLeft)
            counterSecondHalfDiagonalLeft = 0
        }

        return maxSequence
    }
}
