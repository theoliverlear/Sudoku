package org.theoliverlear.v2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sudoku {
    Board board;
    //===========================-Constructors-===============================
    public Sudoku() {
        this.board = new Board();
    }
    //==============================-Methods-=================================

    //-----------------------------Play-Game----------------------------------
    public void playGame() {
        this.board.printBoard();
        this.makeMove();
    }
    //-----------------------------Make-Move----------------------------------
    public void makeMove() {
        // Find a choice of row and column from the user. Ask for the value
        // they want at that placement.
        int row, column;
        // Get row choice
        System.out.println("Select row (1-9): ");
        row = this.getIntUserInput();
        // Get column choice
        System.out.println("Select column (1-9): ");
        column = this.getIntUserInput();
        // Get value choice
        System.out.println("Select value (1-9): ");
        int value = this.getIntUserInput();
        // Place the value at the row and column choice.
        this.board.placeNumber(row, column, value);
    }
    //-------------------------Get-Int-User-Input-----------------------------
    public int getIntUserInput() {
        Scanner intUserInput = new Scanner(System.in);
        int choice = -1;
        boolean validInput = false;
        // While the input is not an integer or is not between 1 and 9, keep
        // asking for input. Once a valid input is received, set validInput to
        // true and return the choice.
        do {
            try {
                choice = intUserInput.nextInt();
                if (choice < 1 || choice > 9) {
                    System.out.println("Please enter a number between " +
                                       "1 and 9.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!validInput);
        return choice;
    }

}
