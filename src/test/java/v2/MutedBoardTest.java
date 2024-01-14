package v2;

import org.junit.Test;
import org.theoliverlear.model.sudoku.MutedBoard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutedBoardTest {
    int[][] board = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
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
    @Test
    public void testMutedIndices() {
        this.mutedBoard.placeNumber(1, 1, 5);
        this.mutedBoard.printBoard();
        assertFalse(this.mutedBoard.getNumber(1, 1) == 5);
    }
}
