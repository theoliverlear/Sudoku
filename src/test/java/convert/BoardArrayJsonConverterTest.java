package convert;

import org.junit.jupiter.api.Test;
import org.theoliverlear.entity.convert.BoardArrayJsonConverter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardArrayJsonConverterTest {
    int[][] testBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 0, 0, 0}
    };
    String testExpectedBoardJson = "[[0,0,0,0,0,0,0,0,0]," +
                                   "[7,0,0,0,0,0,0,0,0]," +
                                   "[0,4,0,0,0,0,0,0,0]," +
                                   "[0,0,1,0,0,0,6,0,0]," +
                                   "[0,0,0,0,0,0,0,9,0]," +
                                   "[0,0,0,0,0,0,0,0,5]," +
                                   "[0,0,0,0,0,0,0,0,0]," +
                                   "[9,0,0,0,0,0,0,0,0]," +
                                   "[0,8,0,0,0,0,0,0,0]]";
    String testFailExpectedBoardJson = "[[1,0,0,0,0,0,0,0,0]," +
                                       "[7,0,0,0,0,0,0,0,0]," +
                                       "[0,4,0,0,0,0,0,0,0]," +
                                       "[0,0,1,0,0,0,6,0,0]," +
                                       "[0,0,0,0,0,0,0,9,0]," +
                                       "[0,0,0,0,0,0,0,0,5]," +
                                       "[0,0,0,0,0,0,0,0,0]," +
                                       "[9,0,0,0,0,0,0,0,0]," +
                                       "[0,8,0,0,0,0,0,0,0]]";
    BoardArrayJsonConverter converter = new BoardArrayJsonConverter();
    @Test
    public void testConvertToDatabaseColumn() {
        String convertedBoard = this.converter.convertToDatabaseColumn(this.testBoard);
        boolean convertedBoardMatchesExpected = convertedBoard.equals(this.testExpectedBoardJson);
        assertTrue(convertedBoardMatchesExpected);

        boolean convertedBoardMatchesFailBoard = convertedBoard.equals(this.testFailExpectedBoardJson);
        assertFalse(convertedBoardMatchesFailBoard);
    }
}
