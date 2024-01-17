package v2;

import org.junit.jupiter.api.Test;
import org.theoliverlear.entity.Board;
import org.theoliverlear.model.sudoku.SudokuGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGeneratorTest {
    SudokuGenerator sudokuGeneratorTest = new SudokuGenerator();
    @Test
    public void testIsSameBoard() {
        this.sudokuGeneratorTest.getSudoku().getBoard().resetBoard();
        int[][] expected = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        Board expectedBoard = new Board(expected);
        Board actualBoard = this.sudokuGeneratorTest.getSudoku().getBoard();
        boolean boardMatches = this.sudokuGeneratorTest.isSameBoard(expectedBoard, actualBoard);
        assertTrue(boardMatches);

        this.sudokuGeneratorTest.getSudoku().getBoard().placeNumber(1, 1, 1);
        boardMatches = this.sudokuGeneratorTest.isSameBoard(expectedBoard, actualBoard);
        assertFalse(boardMatches);
    }
    // Test the muted board
    int[][] board = {
            {2, 6, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 9
    };
    SudokuGenerator sudokuGeneratorMutedBoardTest = new SudokuGenerator();
    @Test
    public void testMutedIndices() {
        // TODO: Implement new Board's muted indices
        this.sudokuGeneratorMutedBoardTest.generateBoardSequentially();
        this.sudokuGeneratorMutedBoardTest.getSudoku().getBoard().printBoard();
        assertTrue(this.sudokuGeneratorMutedBoardTest.getSudoku().getBoard().getNumber(1, 1) == 2);
        assertTrue(this.sudokuGeneratorMutedBoardTest.getSudoku().getBoard().getNumber(1, 2) == 6);
    }
    // Test muted board generator
    int[][] emptyMutedBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
            {0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
            {0, 0, 0, 0, 0, 0, 0, 0, 0}  // 9
    };
    SudokuGenerator sudokuGeneratorMutedBoardGeneratorTest = new SudokuGenerator();
    @Test
    public void testMutedBoardGenerator() {
        this.sudokuGeneratorMutedBoardGeneratorTest.generateValidMutedBoard(81);
        this.sudokuGeneratorMutedBoardGeneratorTest.getSudoku().getBoard().printBoard();
    }
}
