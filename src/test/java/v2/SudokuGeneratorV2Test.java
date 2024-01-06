package v2;

import org.junit.jupiter.api.Test;
import org.theoliverlear.v2.Board;
import org.theoliverlear.v2.MutedBoard;
import org.theoliverlear.v2.SudokuGeneratorV2;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGeneratorV2Test {
    SudokuGeneratorV2 sudokuGeneratorV2Test = new SudokuGeneratorV2();
    @Test
    public void testIsSameBoard() {
        this.sudokuGeneratorV2Test.getSudoku().getBoard().resetBoard();
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
        Board actualBoard = this.sudokuGeneratorV2Test.getSudoku().getBoard();
        boolean boardMatches = this.sudokuGeneratorV2Test.isSameBoard(expectedBoard, actualBoard);
        assertTrue(boardMatches);

        this.sudokuGeneratorV2Test.getSudoku().getBoard().placeNumber(1, 1, 1);
        boardMatches = this.sudokuGeneratorV2Test.isSameBoard(expectedBoard, actualBoard);
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
    MutedBoard mutedBoard = new MutedBoard(board);
    SudokuGeneratorV2 sudokuGeneratorV2MutedBoardTest = new SudokuGeneratorV2(mutedBoard);
    @Test
    public void testMutedIndices() {
        this.sudokuGeneratorV2MutedBoardTest.generateBoard();
        this.sudokuGeneratorV2MutedBoardTest.getSudoku().getBoard().printBoard();
        assertTrue(this.sudokuGeneratorV2MutedBoardTest.getSudoku().getBoard().getNumber(1, 1) == 2);
        assertTrue(this.sudokuGeneratorV2MutedBoardTest.getSudoku().getBoard().getNumber(1, 2) == 6);
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
    MutedBoard mutedBoardGeneratorBoard = new MutedBoard(emptyMutedBoard);
    SudokuGeneratorV2 sudokuGeneratorV2MutedBoardGeneratorTest = new SudokuGeneratorV2(mutedBoardGeneratorBoard);
    @Test
    public void testMutedBoardGenerator() {
        this.sudokuGeneratorV2MutedBoardGeneratorTest.generateValidMutedBoard(81);
        this.sudokuGeneratorV2MutedBoardGeneratorTest.getSudoku().getBoard().printBoard();
    }
}
