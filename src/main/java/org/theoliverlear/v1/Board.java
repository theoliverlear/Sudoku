package org.theoliverlear.v1;

import java.util.*;

public class Board {
    int[][] board = new int[9][9];
    public Board() {
        Arrays.stream(this.board).forEach(row -> Arrays.fill(row, 0));
    }
    public void resetBoard() {
        Arrays.stream(this.board).forEach(row -> Arrays.fill(row, 0));
    }
    public void printBoard() {
        String separator = "-".repeat(21);
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + " ");
                if ((j + 1) % 3 == 0 && j != this.board[i].length - 1) {
                    System.out.print("| ");
                }
            }
            if ((i + 1) % 3 == 0 && i != this.board.length - 1) {
                System.out.println("\n" + separator);
            } else {
                System.out.println();
            }
        }
    }
    public boolean isTaken(int row, int column) {
        return this.board[--row][--column] != 0;
    }
    public void placeNumber(int row, int column, int value) {
        this.board[--row][--column] = value;
    }
    public void makeMove() {
        boolean choiceIsTaken = true;
        int row = -1;
        int column = -1;
        do {
            System.out.println("Select row (1-9): ");
            row = this.getIntUserInput();
            System.out.println("Select column (1-9): ");
            column = this.getIntUserInput();
            choiceIsTaken = this.isTaken(row, column);
        } while (choiceIsTaken);
        System.out.println("Select value (1-9): ");
        int value = this.getIntUserInput();
        this.placeNumber(row, column, value);
    }
    public int getIntUserInput() {
        Scanner intUserInput = new Scanner(System.in);
        int choice = -1;
        boolean validInput = false;
        do {
            try {
                choice = intUserInput.nextInt();
                if (choice < 1 || choice > 9) {
                    System.out.println("Please enter a number between " +
                                       "1 and 9.");
                }
                validInput = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!validInput);
        return choice;
    }
    public boolean isWinningBoard() {
        boolean validBoard = true;
        for (int i = 0; i < this.board.length; i++) {
            if (!this.isWinningRow(i) || !this.isWinningColumn(i) ||
                !this.isWinningSquare(i)) {
                validBoard = false;
                break;
            }
        }
        return validBoard;
    }
    public boolean isWinningRow(int row) {
        boolean validRow = true;
        HashSet<Integer> rowSet = new HashSet<>();
        for (int i = 0; i < this.board[row].length; i++) {
            rowSet.add(this.board[row][i]);
        }
        if (rowSet.size() != this.board[row].length) {
            validRow = false;
        }
        return validRow;
    }
    public boolean isWinningColumn(int column) {
        boolean validColumn = true;
        HashSet<Integer> columnSet = new HashSet<>();
        for (int i = 0; i < this.board.length; i++) {
            columnSet.add(this.board[i][column]);
        }
        if (columnSet.size() != this.board.length) {
            validColumn = false;
        }
        return validColumn;
    }
    public boolean isWinningSquare(int square) {
        /*
        Square:
        ---------------
        (1) | (2) | (3)
        ---------------
        (4) | (4) | (5)
        ---------------
        (7) | (8) | (9)
        ---------------
        Grid:
        ------------------------------------------------------------------------
        ([0][0] [0][1] [0][2]) | ([0][3] [0][4] [0][5]) | ([0][6] [0][7] [0][8])

        ([1][0] [1][1] [1][2]) | ([1][3] [1][4] [1][5]) | ([1][6] [1][7] [1][8])

        ([2][0] [2][1] [2][2]) | ([2][3] [2][4] [2][5]) | ([2][6] [2][7] [2][8])
        ------------------------------------------------------------------------
        ([3][0] [3][1] [3][2]) | ([3][3] [3][4] [3][5]) | ([3][6] [3][7] [3][8])

        ([4][0] [4][1] [4][2]) | ([4][3] [4][4] [4][5]) | ([4][6] [4][7] [4][8])

        ([5][0] [5][1] [5][2]) | ([5][3] [5][4] [5][5]) | ([5][6] [5][7] [5][8])
        ------------------------------------------------------------------------
        ([6][0] [6][1] [6][2]) | ([6][3] [6][4] [6][5]) | ([6][6] [6][7] [6][8])

        ([7][0] [7][1] [7][2]) | ([7][3] [7][4] [7][5]) | ([7][6] [7][7] [7][8])

        ([8][0] [8][1] [8][2]) | ([8][3] [8][4] [8][5]) | ([8][6] [8][7] [8][8])
        ------------------------------------------------------------------------

        The pattern for square 1 to 3 in rows is no change. The same applies
        as to the next two patterns of three rows.
        Formula start row index:
        if (square > 3 && square < 7) {
            startRow = 3;
        } else if (square > 6) {
            startRow = 6;
        } else {
            startRow = 0;
        }
        Formula end row index:
        endRow = startRow + 3;

        The patter for columns from square 1 to 2 is an increase of 3. The
        same applies to the next column increasing another 3. The pattern
        resets and repeats every three rows.
        Formula start column index:
        if (square % 3 == 0) {
            startColumn = 6;
         } else if (square % 3 == 2) {
            startColumn = 3;
         } else {
            startColumn = 0;
         }
        Formula end column index:
        endColumn = startColumn + 3;
         */
        HashSet<Integer> squareSet = new HashSet<>();
        int[][] squareIndices = this.getSquareIndices(square);
        int startRow = squareIndices[0][0];
        int endRow = squareIndices[0][1];
        int startColumn = squareIndices[1][0];
        int endColumn = squareIndices[1][1];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                squareSet.add(this.board[i][j]);
            }
        }
        return squareSet.size() == 9;
    }
    public int[][] getSquareIndices(int square) {
        int startRow = 0;
        int endRow = 0;
        int startColumn = 0;
        int endColumn = 0;
        if (square > 3 && square < 7) {
            startRow = 3;
        } else if (square > 6) {
            startRow = 6;
        } else {
            startRow = 0;
        }
        endRow = startRow + 3;
        if (square % 3 == 0) {
            startColumn = 6;
        } else if (square % 3 == 2) {
            startColumn = 3;
        } else {
            startColumn = 0;
        }
        endColumn = startColumn + 3;
        return new int[][] {
            {startRow, endRow},
            {startColumn, endColumn}
        };
    }
    public void checkAnnouncements() {
        for (int i = 0; i < this.board.length; i++) {
            if (this.isWinningRow(i)) {
                this.announceWinningRow(i);
            }
            if (this.isWinningColumn(i)) {
                this.announceWinningColumn(i);
            }
        }
    }
    public void announceWinningRow(int row) {
        System.out.println("Row " + (row + 1) + " is complete!");
    }
    public void announceWinningColumn(int column) {
        System.out.println("Column " + (column + 1) + " is complete!");
    }
    public boolean rowContainsValue(int row, int value) {
        boolean containsValue = false;
        for (int i = 0; i < this.board[row].length; i++) {
            if (this.board[row][i] == value) {
                containsValue = true;
                break;
            }
        }
        return containsValue;
    }
    public boolean columnContainsValue(int column, int value) {
        boolean containsValue = false;
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][column] == value) {
                containsValue = true;
                break;
            }
        }
        return containsValue;
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }
}
