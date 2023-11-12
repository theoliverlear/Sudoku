package org.theoliverlear.v2;

import java.util.ArrayList;
import java.util.Arrays;

public class SolutionGenerator {
    Sudoku sudoku;
    final int rowLength;
    final int colLength;
    //===========================-Constructors-===============================
    public SolutionGenerator() {
        this.sudoku = new Sudoku();
        this.rowLength = this.sudoku.getBoard().getRowLength();
        this.colLength = this.sudoku.getBoard().getColLength();
    }
    public void setUp() {
        // Option 1
//        this.sudoku.getBoard().setBoard(new int[][] {
//            {9, 8, 3, 4, 5, 6, 7, 2, 1},
//            {4, 5, 6, 7, 8, 9, 1, 2, 3},
//            {7, 8, 9, 1, 2, 3, 4, 5, 6},
//            {2, 3, 4, 5, 6, 7, 8, 9, 1},
//            {5, 6, 7, 8, 9, 1, 2, 3, 4},
//            {8, 9, 1, 2, 3, 4, 5, 6, 7},
//            {3, 4, 5, 6, 7, 8, 9, 1, 2},
//            {6, 7, 8, 9, 1, 2, 3, 4, 5},
//            {9, 1, 2, 3, 4, 5, 6, 7, 8}
//        });
        // Option 2
//        this.sudoku.getBoard().setBoard(new int[][] {
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//        });
        // Option 3
//        this.sudoku.getBoard().setBoard(new int[][] {
//            {1, 2, 3, 4, 5, 6, 7, 8, 9},
//            {4, 5, 6, 1, 8, 9, 7, 2, 3},
//            {7, 8, 9, 1, 2, 3, 4, 5, 6},
//            {2, 3, 4, 5, 6, 7, 8, 9, 1},
//            {5, 6, 7, 8, 9, 1, 2, 3, 4},
//            {8, 9, 1, 2, 3, 4, 5, 6, 7},
//            {3, 4, 5, 6, 7, 8, 9, 1, 2},
//            {6, 7, 8, 9, 1, 2, 3, 4, 5},
//            {9, 1, 2, 3, 4, 5, 6, 7, 8}
//        });
        // Option 4 - Winning board
        int[][] winningBoard = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 5 - Close to winning board - row 1
        int[][] closeWinBoardRowOne = {
                {2, 1, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 6 - Close to winning board - row 2
        int[][] closeWinBoardRowTwo = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {5, 4, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 7 - Close to winning board - row 1 and 2
        int[][] closeWinBoardRowOneTwo = {
                {2, 1, 3, 4, 5, 6, 7, 8, 9},
                {5, 4, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 8 - Close to winning board - row 1, 2, and 5
        int[][] closeWinBoardRowOneTwoFive = {
                {2, 1, 3, 4, 5, 6, 7, 8, 9},
                {5, 4, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 3, 1, 2, 9, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 9 - Close to winning board - row 1 middle nums
        int[][] rowOneMiddleNum = {
                {1, 2, 3, 4, 8, 6, 7, 5, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 10 - Row 1 multiple violations
        int[][] rowOneMultipleViolations = {
                {1, 5, 3, 8, 2, 6, 7, 4, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 11 - Row 1 and 2 multiple violations
        int[][] rowOneTwoMultipleSingleViolations = {
                {1, 5, 3, 8, 2, 6, 7, 4, 9},
                {4, 5, 7, 6, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        // Option 12 - Row 3
        int[][] rowThree = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 5, 1, 2, 3, 4, 9, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        this.sudoku.getBoard().setBoard(closeWinBoardRowTwo);
    }
    public ArrayList<Integer[]> fetchViolatingIndexes(int row) {
        int[] rowSet = this.sudoku.getBoard().fetchSetFromRow(row);
        int targetNum;
        ArrayList<Integer[]> violatingIndex = new ArrayList<>();
        for (int i = 0; i < rowSet.length; i++) {
            targetNum = rowSet[i];
            int[] colSet = this.sudoku.getBoard().fetchSetFromColumn(i + 1);
            for (int j = 1; j < colSet.length; j++) {
                if (colSet[j] == targetNum) {
                    violatingIndex.add(new Integer[] {j, i});
                }

            }
        }
//        System.out.println("Violations Pre " + violatingIndex.size());
//        for (Integer[] pair : violatingIndex) {
//            System.out.println(Arrays.toString(pair));
//        }
        for (int sizeIndex = 0; sizeIndex < violatingIndex.size() - 1; sizeIndex++) {
            int currentCol = violatingIndex.get(sizeIndex)[1];
            int nextCol = violatingIndex.get(sizeIndex + 1)[1];
            if (currentCol == nextCol) {
                violatingIndex.remove(sizeIndex + 1);
                //System.out.println("Removed duplicate violation.");
            }
        }
//        System.out.println("Violations Post " + violatingIndex.size());
        return violatingIndex;
    }
    public void swapViolations(int row, ArrayList<Integer[]> violationIndexes) {
        // Row is the row to swap violations in. The index is one less than
        // the row number because the array is zero indexed.
        int rowIndex = row - 1;
        // Loop until winning board is found, or until there are no more
        // violations.
        outer: while (true) {
            if (violationIndexes.isEmpty()) {
                System.out.println("Possible solution found!");
                break;
            }
            // Create an array of the violating indexes at the row position
            // while maintaining the column position to swap the numbers in
            // the row.
            int[][] rowViolationsIndexes = new int[violationIndexes.size()][2];
            for (int i = 0; i < violationIndexes.size(); i++) {
                // The row index is the same for all violations in the row.
                // The column index is the same as the column index of the
                // violating number.
                Integer[] violationIndex = violationIndexes.get(i);
                rowViolationsIndexes[i] = new int[] {rowIndex, violationIndex[1]};
            }
            // Show all the positions of the violations at the point of being
            // swapped.
            for (int[] pair : rowViolationsIndexes) {
                System.out.println(Arrays.toString(pair));
            }
            // Loop through all the violations and swap the violating numbers
            // with the next violating number.
            for (int i = 0; i < rowViolationsIndexes.length - 1; i++) {
                int[] initialIndex = rowViolationsIndexes[i];
                int[] targetIndex = rowViolationsIndexes[i + 1];
                int initialRow = initialIndex[0] + 1;
                int initialCol = initialIndex[1] + 1;
                int targetRow = targetIndex[0] + 1;
                int targetCol = targetIndex[1] + 1;
                int initialNum = this.sudoku.getBoard().getNumber(initialRow, initialCol);
                int targetNum = this.sudoku.getBoard().getNumber(targetRow, targetCol);
                System.out.printf("""
                                Row: %d, Col: %d, Num: %d
                                Row: %d, Col: %d, Num: %d%n""",
                        initialRow, initialCol, initialNum,
                        targetRow, targetCol, targetNum);
                this.sudoku.getBoard().placeNumber(initialRow, initialCol, targetNum);
                this.sudoku.getBoard().placeNumber(targetRow, targetCol, initialNum);
                this.sudoku.getBoard().printBoard();

                if (!this.sudoku.board.isWinningBoard()) {
                    System.out.println("Not a winning board.");
                } else {
                    System.out.println("Winning board found!");
                    break outer;
                }
            }
            violationIndexes = this.fetchViolatingIndexes(row);
        }
    }
    public void findSwapAllViolations() {
        // Loop until winning board is found. Perhaps not in the called method
        // but here instead.
        while (!this.sudoku.getBoard().isWinningBoard()) {
            for (int i = 1; i <= this.rowLength; i++) {
                ArrayList<Integer[]> violatingIndexes = this.fetchViolatingIndexes(i);
                this.swapViolations(i, violatingIndexes);
                if (this.sudoku.getBoard().isWinningBoard()) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SolutionGenerator solutionGenerator = new SolutionGenerator();
        solutionGenerator.setUp();
        solutionGenerator.sudoku.getBoard().printBoard();
        solutionGenerator.findSwapAllViolations();

    }
}
