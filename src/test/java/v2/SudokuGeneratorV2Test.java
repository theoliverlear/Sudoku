package v2;

import org.junit.jupiter.api.Test;
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
        int[][] actual = this.sudokuGeneratorV2Test.getSudoku().getBoard().getBoard();
        boolean boardMatches = this.sudokuGeneratorV2Test.isSameBoard(expected, actual);
        assertTrue(boardMatches);

        this.sudokuGeneratorV2Test.getSudoku().getBoard().placeNumber(1, 1, 1);
        boardMatches = this.sudokuGeneratorV2Test.isSameBoard(expected, actual);
        assertFalse(boardMatches);
    }
}
