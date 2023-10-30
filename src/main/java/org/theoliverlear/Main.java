package org.theoliverlear;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] board = new int[9][9];
    static Scanner strUserInput = new Scanner(System.in);
    static Scanner intUserInput = new Scanner(System.in);
    public static void printBoard() {
        String separator = "-".repeat(21);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0 && j != board[i].length - 1) {
                    System.out.print("| ");
                }
            }
            if ((i + 1) % 3 == 0 && i != board.length - 1) {
                System.out.println("\n" + separator);
            } else {
                System.out.println();
            }
        }
    }
    public static void makeMove() {
        System.out.println("Select row (1-9): ");
        int row = intUserInput.nextInt();
        System.out.println("Select column (1-9): ");
        int column = intUserInput.nextInt();
        System.out.println("Select value (1-9): ");
        int value = intUserInput.nextInt();
        board[row - 1][column - 1] = value;
    }
    public static boolean validRow(int row) {
        boolean validRow = true;
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] != (i + 1)) {
                validRow = false;
                break;
            }
        }
        return validRow;
    }
    public static void chooseValidateRow() {
        System.out.println("Choose a row to validate...");
        System.out.println("Select row (1-9): ");
        int row = intUserInput.nextInt();
        System.out.println("Row is complete: " + validRow(row - 1));
    }
    public static void main(String[] args) {
        Arrays.stream(board).forEach(row -> Arrays.fill(row, 0));
        boolean continuePlaying = true;
        String continuePlayingChoice;
        String rowValidationChoice;
        printBoard();
        do {
            makeMove();
            printBoard();
            chooseValidateRow();
            System.out.println("Continue playing? (y/n): ");
            if (strUserInput.nextLine().equals("n")) {
                continuePlaying = false;
            }
        } while (continuePlaying);
    }
}