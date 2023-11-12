package org.theoliverlear.v2;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    int[][] board;
    final int colLength;
    final int rowLength;
    //===========================-Constructors-===============================
    public Board() {
        this.board = new int[9][9];
        this.rowLength = this.board.length;
        this.colLength = this.board[0].length;
        this.resetBoard();
    }
    //==============================-Methods-=================================

    //-----------------------------Reset-Board--------------------------------
    public void resetBoard() {
        // Stream through each row and fill it with zeros.
        Arrays.stream(this.board).forEach(row -> Arrays.fill(row, 0));
    }
    //-----------------------------Print-Board--------------------------------
    public void printBoard() {
        String separator = "-".repeat(21);
        // Print the board by going through each row and its columns
        for (int rowIndex = 0; rowIndex < this.rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < this.colLength; colIndex++) {
                // Print the value at the current row and column with a space
                // to separate it from the next value.
                System.out.print(this.board[rowIndex][colIndex] + " ");
                // If the column is divisible by three that means that there
                // should be a separator represented by the pipe "|". If it is
                // the last column then there should be no separator just as
                // there is not one in the starting column.
                if ((colIndex + 1) % 3 == 0 && colIndex != this.colLength - 1) {
                    System.out.print("| ");
                }
            }
            // After completing any row, a new line is printed to start the
            // next row.

            // Once completing a full row, check if the row is divisible by
            // three. If it is there should be a separator represented by the
            // "-" symbols. If it is the last row then there should be no just
            // as there is not one in the starting row.
            if ((rowIndex + 1) % 3 == 0 && rowIndex != this.rowLength - 1) {
                System.out.println("\n" + separator);
            } else {
                System.out.println();
            }
        }
    }
    //-----------------------------Is-Taken-----------------------------------
    public boolean isTaken(int row, int column) {
        // The numbers being received should be the row and column numbers,
        // not their indexes. They are converted to indexes by decrementing.
        return this.board[--row][--column] != 0;
    }
    //-----------------------------Get-Number---------------------------------
    public int getNumber(int row, int column) {
        return this.board[--row][--column];
    }
    //-----------------------------Place-Number-------------------------------
    public void placeNumber(int row, int column, int value) {
        // The numbers being received should be the row and column numbers,
        // not their indexes. They are converted to indexes by decrementing.
        this.board[--row][--column] = value;
    }
    //--------------------------Is-Complete-Set-------------------------------
    public boolean isCompleteSet(int[] set) {
        // If the set contains a zero, then it is not complete.
        for (int num : set) {
            if (num == 0) {
                return false;
            }
        }
        return true;
    }
    //----------------------------Is-Valid-Set--------------------------------
    public boolean isValidSet(int[] set) {
        // Make an array which holds all the nums in the set. If the set
        // contains a zero, then it is not valid. If the set contains all the
        // numbers from 1 to 9 without duplicates, then it is valid.
        ArrayList<Integer> setNums = new ArrayList<>();
        for (int num : set) {
            if (num == 0) {
                return false;
            } else if (!setNums.contains(num)) {
                setNums.add(num);
            }
        }
        return setNums.size() == 9;
    }
    //-------------------------Fetch-Set-From-Row-----------------------------
    public int[] fetchSetFromRow(int row) {
        // The row array can be fetched by calling the index of the row minus
        // minus one.
        return this.board[--row];
    }
    //-----------------------Fetch-Set-From-Column----------------------------
    public int[] fetchSetFromColumn(int column) {
        int[] columnSet = new int[9];
        for (int i = 0; i < this.colLength; i++) {
            columnSet[i] = this.board[i][column - 1];
        }
        return columnSet;
    }
    //-----------------------Fetch-Set-From-Square----------------------------
    public int[] fetchSetFromSquare(int square) {
        // Get the indices of the square.
        int[][] squareIndices = this.fetchSquareIndices(square);
        // Create the variables which bound the loop.
        int startRow = squareIndices[0][0];
        int endRow = squareIndices[0][1];
        int startCol = squareIndices[1][0];
        int endCol = squareIndices[1][1];
        // Create a set of the numbers in the square.
        int[] squareSet = new int[9];
        int squareSetIndex = 0;
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                squareSet[squareSetIndex] = this.board[i][j];
                squareSetIndex++;
            }
        }
        return squareSet;
    }
    //-----------------------Fetch-Square-Indices-----------------------------
    public int[][] fetchSquareIndices(int square) {
        int startRow, endRow, startCol, endCol;
        if (square > 3 && square < 7) {
            startRow = 3;
        } else if (square > 6) {
            startRow = 6;
        } else {
            startRow = 0;
        }
        // End row will always be three more than the start row because each
        // square is three rows long. Two is added to the start row because
        // the row itself is one of the three rows.
        endRow = startRow + 2;
        if (square % 3 == 0) {
            startCol = 6;
        } else if (square % 3 == 2) {
            startCol = 3;
        } else {
            startCol = 0;
        }
        // End column will always be three more than the start column because
        // each square is three columns long. Two is added to the start column
        // because the column itself is one of the three columns.
        endCol = startCol + 2;
        return new int[][] {
                {startRow, endRow},
                {startCol, endCol}};
    }
    public int fetchSquare(int row, int column) {
        int square = 0;
        if (row <= 3 && column <= 3) {
            square = 1;
        } else if (row <= 3 && column <= 6) {
            square = 2;
        } else if (row <= 3 && column <= 9) {
            square = 3;
        } else if (row <= 6 && column <= 3) {
            square = 4;
        } else if (row <= 6 && column <= 6) {
            square = 5;
        } else if (row <= 6 && column <= 9) {
            square = 6;
        } else if (row <= 9 && column <= 3) {
            square = 7;
        } else if (row <= 9 && column <= 6) {
            square = 8;
        } else if (row <= 9 && column <= 9) {
            square = 9;
        }
        return square;
    }
    //--------------------------Is-Winning-Board------------------------------
    public boolean isWinningBoard() {
        // Check each row, column, and square to see if they are valid sets.
        // If any of them fail, then the board is not a winning board. If all
        // of them pass, then the board is a winning board.
        for (int i = 0; i < this.rowLength; i++) {
            // Row nums
            int[] rowSet = this.fetchSetFromRow(i + 1);
            // Column nums
            int[] colSet = this.fetchSetFromColumn(i + 1);
            // Square nums
            int[] squareSet = this.fetchSetFromSquare(i + 1);
            if (!this.isValidSet(rowSet) || !this.isValidSet(colSet) ||
                                            !this.isValidSet(squareSet)) {
                return false;
            }
        }
        return true;
    }
    //=============================-Overrides-=================================
    public boolean equals(Board board) {
        return Arrays.deepEquals(this.board, board.getBoard());
    }
    //=============================-Getters-==================================
    public int[][] getBoard() {
        return this.board;
    }
    public int getRowLength() {
        return this.rowLength;
    }
    public int getColLength() {
        return this.colLength;
    }
    //=============================-Setters-==================================
    public void setBoard(int[][] board) {
        this.board = board;
    }
}
