package v2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.theoliverlear.model.Board;
import org.theoliverlear.model.Sudoku;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuTest {
    Sudoku testSudoku = new Sudoku();
    @BeforeEach
    public void setUp() {
        this.testSudoku.getBoard().setBoard(new int[][] {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
        });
    }
    @Test
    public void testFetchSquareIndices() {
        int[][] expected = {{0, 2}, {0, 2}};
        int[][] actual = this.testSudoku.getBoard().fetchSquareIndices(1);
        assertArrayEquals(expected, actual);

        expected = new int[][] {{0, 2}, {3, 5}};
        actual = this.testSudoku.getBoard().fetchSquareIndices(2);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void testWinningBoard() {
        assertTrue(this.testSudoku.getBoard().isWinningBoard());

        this.testSudoku.getBoard().placeNumber(1, 1, 8);
        assertFalse(this.testSudoku.getBoard().isWinningBoard());
    }
    @Test
    public void testFetchSetFromRow() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actual = this.testSudoku.getBoard().fetchSetFromRow(1);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void testFetchSetFromColumn() {
        int[] expected = {1, 4, 7, 2, 5, 8, 3, 6, 9};
        int[] actual = this.testSudoku.getBoard().fetchSetFromColumn(1);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void testFetchSetFromSquare() {
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actual = this.testSudoku.getBoard().fetchSetFromSquare(1);
        assertArrayEquals(expected, actual);
    }
    @Test
    public void testFetchSquare() {
        int expected = 1;
        int actual = this.testSudoku.getBoard().fetchSquare(1, 1);
        assertTrue(expected == actual);

        expected = 2;
        actual = this.testSudoku.getBoard().fetchSquare(1, 4);
        assertTrue(expected == actual);

        expected = 8;
        actual = this.testSudoku.getBoard().fetchSquare(7, 4);
        assertTrue(expected == actual);

        expected = 9;
        actual = this.testSudoku.getBoard().fetchSquare(7, 7);
        assertTrue(expected == actual);
    }
    @Test
    public void testPlaceNumber() {
        this.testSudoku.getBoard().placeNumber(1, 1, 8);
        int expected = 8;
        int actual = this.testSudoku.getBoard().getNumber(1, 1);
        assertTrue(expected == actual);
    }
    @Test
    public void testBoardEquals() {
        Board cloneBoard = new Board();
        cloneBoard.setBoard(new int[][] {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
        });
        assertTrue(this.testSudoku.getBoard().equals(cloneBoard));
    }
}
