package xoGame

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Assertions.*

class XOGameFieldTest {

    @Nested
    inner class Field {

        @Test
        fun `creates with correct size`() {
            val testField = XOGameField(5)

            assertEquals(5, testField.size)
        }

        @Test
        fun `fails with negative size`() {
            assertThrows(IllegalArgumentException::class.java) {
                XOGameField(-1)
            }
        }

        @Test
        fun `fails with zero size`() {
            assertThrows(IllegalArgumentException::class.java) {
                XOGameField(0)
            }
        }
    }

    @Nested
    inner class Get {

        @Test
        fun `throw IllegalArgumentException when arguments out of field size`() {
            assertThrows(IllegalArgumentException::class.java) {
                XOGameField(5).get(Cell(6, 0))
            }

            assertThrows(IllegalArgumentException::class.java) {
                XOGameField(5).get(Cell(6, 0))
            }
        }

        @Test
        fun `return that all cells of new field is empty`() {

            val size = 5

            val newField = XOGameField(size)

            for (row in 0 until size) {
                for (column in 0 until size) {

                    assertEquals(newField.get(row, column), XOGameSign.EMPTY)
                }
            }
        }
    }

    @Nested
    inner class Set {

        @Test
        fun `write correct value to cell`() {

            val size = 5
            val newField = XOGameField(size)

            newField.set(Cell(0, 0), XOGameSign.X)
            newField.set(Cell(0, 1), XOGameSign.X)
            newField.set(Cell(0, 2), XOGameSign.X)

            newField.set(Cell(1, 0), XOGameSign.O)
            newField.set(Cell(1, 1), XOGameSign.O)
            newField.set(Cell(1, 2), XOGameSign.O)

            assertEquals(newField.get(0, 0), XOGameSign.X)
            assertEquals(newField.get(0, 1), XOGameSign.X)
            assertEquals(newField.get(0, 2), XOGameSign.X)

            assertEquals(newField.get(1, 0), XOGameSign.O)
            assertEquals(newField.get(1, 1), XOGameSign.O)
            assertEquals(newField.get(1, 2), XOGameSign.O)
        }

        @Test
        fun `throws IllegalArgumentException when values are out of field's size`() {

            assertThrows(IllegalArgumentException::class.java) {
                XOGameField(5).set(Cell(6, 0), XOGameSign.O)
            }

            assertThrows(IllegalArgumentException::class.java) {
                XOGameField(5).set(Cell(0, 6), XOGameSign.X)
            }
        }

        @Test
        fun `can't overwrite cell and throws IllegalArgumentException`() {

            val newField = XOGameField(5)

            newField.set(Cell(1, 1), XOGameSign.X)
            newField.set(Cell(2, 2), XOGameSign.O)

            assertThrows(IllegalArgumentException::class.java) {
                newField.set(Cell(1, 1), XOGameSign.O)
            }

            assertThrows(IllegalArgumentException::class.java) {
                newField.set(Cell(2, 2), XOGameSign.X)
            }
        }
    }

    @Nested
    inner class Add {

        @Test
        fun `X to Cell`() {

            val newField = XOGameField(5)

            newField.cellAddX(Cell(1, 1))
            newField.cellAddX(Cell(4, 4))

            assertEquals(newField.get(1, 1), XOGameSign.X)
            assertEquals(newField.get(4, 4), XOGameSign.X)
        }

        @Test
        fun `O to Cell`() {

            val newField = XOGameField(5)

            newField.cellAddO(Cell(1, 1))
            newField.cellAddO(Cell(4, 4))

            assertEquals(newField.get(1, 1), XOGameSign.O)
            assertEquals(newField.get(4, 4), XOGameSign.O)
        }
    }

    @Nested
    inner class FindLargestSequence {

        @Test
        fun horizontally() {

            val newField = XOGameField(5)


            newField.set(Cell(0, 0), XOGameSign.X)
            newField.set(Cell(0, 1), XOGameSign.X)
            newField.set(Cell(0, 2), XOGameSign.X)
            newField.set(Cell(0, 3), XOGameSign.X)
            newField.set(Cell(0, 4), XOGameSign.X)

            newField.set(Cell(2, 2), XOGameSign.X)
            newField.set(Cell(2, 3), XOGameSign.X)
            newField.set(Cell(2, 4), XOGameSign.X)


            newField.set(Cell(3, 0), XOGameSign.O)
            newField.set(Cell(3, 1), XOGameSign.O)
            newField.set(Cell(3, 2), XOGameSign.O)
            newField.set(Cell(3, 3), XOGameSign.O)
            newField.set(Cell(3, 4), XOGameSign.O)

            newField.set(Cell(4, 2), XOGameSign.O)
            newField.set(Cell(4, 3), XOGameSign.O)
            newField.set(Cell(4, 4), XOGameSign.O)


            assertEquals(newField.findLongestSequenceHorizontally(XOGameSign.X), 5)
            assertEquals(newField.findLongestSequenceHorizontally(XOGameSign.O), 5)
        }

        @Test
        fun vertically() {

            val newField = XOGameField(4)

            newField.set(Cell(0, 0), XOGameSign.X)
            newField.set(Cell(1, 0), XOGameSign.X)
            newField.set(Cell(2, 0), XOGameSign.X)
            newField.set(Cell(3, 0), XOGameSign.X)

            newField.set(Cell(0, 1), XOGameSign.O)
            newField.set(Cell(1, 1), XOGameSign.O)

            newField.set(Cell(2, 1), XOGameSign.X)
            newField.set(Cell(3, 1), XOGameSign.X)

            newField.set(Cell(0, 2), XOGameSign.O)
            newField.set(Cell(1, 2), XOGameSign.O)
            newField.set(Cell(2, 2), XOGameSign.O)
            newField.set(Cell(3, 2), XOGameSign.X)


            assertEquals(newField.findLongestSequenceVertically(XOGameSign.X), 4)
            assertEquals(newField.findLongestSequenceVertically(XOGameSign.O), 3)
        }

        @Test
        fun diagonally() {

            val newField = XOGameField(5)

            newField.set(Cell(0, 0), XOGameSign.X)
            newField.set(Cell(1, 1), XOGameSign.X)
            newField.set(Cell(2, 2), XOGameSign.X)
            newField.set(Cell(3, 3), XOGameSign.X)
            newField.set(Cell(4, 4), XOGameSign.X)

            newField.set(Cell(0, 4), XOGameSign.O)
            newField.set(Cell(1, 3), XOGameSign.O)

            newField.set(Cell(4, 2), XOGameSign.X)

            newField.set(Cell(3, 0), XOGameSign.O)


            assertEquals(newField.findLongestSequenceDiagonally(XOGameSign.X), 5)
            assertEquals(newField.findLongestSequenceDiagonally(XOGameSign.O), 2)
        }
    }
}