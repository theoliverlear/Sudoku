package org.theoliverlear.v2;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    int[][] board;
    int colLength = 9;
    int rowLength = 9;
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
    //-----------------------------Place-Number-------------------------------
    public void placeNumber(int row, int column, int value) {
        // The numbers being received should be the row and column numbers,
        // not their indexes. They are converted to indexes by decrementing.
        this.board[--row][--column] = value;
    }
    public boolean isCompleteSet(int[] set) {
        ArrayList<Integer> setNums = new ArrayList<>();
        for (int num : set) {
            if (num != 0) {
                if (setNums.contains(num)) {
                    return false;
                } else {
                    setNums.add(num);
                }
            }
        }
    }
}
