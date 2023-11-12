import org.junit.Test;
import org.theoliverlear.v1.Sudoku;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuTest {
    Sudoku testSudoku = new Sudoku();
    @Test
    public void testWinningBoardLose() {
        this.testSudoku.determineResult();
        assertFalse(this.testSudoku.getBoard().isWinningBoard());
    }
    @Test
    public void testWinningBoardWin() {
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
        this.testSudoku.determineResult();
        assertTrue(this.testSudoku.getBoard().isWinningBoard());
    }
}
