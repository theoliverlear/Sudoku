package org.theoliverlear;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class SudokuGenerator {
    Board board;
    public SudokuGenerator() {
        this.board = new Board();
    }
    /*
    To discover winning boards, we need to know the rules. Each row will
    have numbers 1-9, all columns will have 1-9, and each square will have
    1-9. How can I efficiently generate a board that meets these criteria?
     */
    //------------------------Find-Complete-Board-----------------------------
    public void findCompleteBoard() {
        /*
        1. Generate a set of numbers for the square.
        2.
         */
    }
    //------------------------Generate-Single-Set-----------------------------
    public int[] generateSingleSet() {
        Random random = new Random();
        ArrayList<Integer> numberSet = new ArrayList<Integer>();
        while (numberSet.size() < 9) {
            int randNum = random.nextInt(9) + 1;
            if (!numberSet.contains(randNum)) {
                numberSet.add(randNum);
            }
        }
        int[] numberArray = new int[9];
        int i = 0;
        for (int number : numberSet) {
            numberArray[i] = number;
            i++;
        }
        return numberArray;
    }
    //----------------------Generate-Full-Number-Set--------------------------
    public void generateRandomNumberSet() {
        int[][] numberSet = new int[9][9];
        int i = 0;
        while (i < numberSet.length) {
            if (this.board.isWinningSquare(i + 1)) {
                numberSet[i] = this.generateSingleSet();
            }
            i++;
        }
        this.board.setBoard(numberSet);
    }
    public void generateSudokuBoard() {

    }
    public void generateStartingBoard() {

    }
    public static void main(String[] args) {
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        sudokuGenerator.generateRandomNumberSet();
        sudokuGenerator.getBoard().printBoard();
        System.out.println(sudokuGenerator.getBoard().isWinningBoard());
        while (!sudokuGenerator.getBoard().isWinningBoard()) {
            sudokuGenerator.generateRandomNumberSet();
            sudokuGenerator.getBoard().printBoard();
        }
    }
    public Board getBoard() {
        return this.board;
    }
}
