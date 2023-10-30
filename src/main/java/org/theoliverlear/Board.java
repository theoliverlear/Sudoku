package org.theoliverlear;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                getIntUserInput();
            }
        } while (!validInput);
        return choice;
    }
    public boolean isWinningBoard() {
        boolean validBoard = true;
        for (int i = 0; i < this.board.length; i++) {
            if (!this.isWinningRow(i) || !this.isWinningColumn(i)) {
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
