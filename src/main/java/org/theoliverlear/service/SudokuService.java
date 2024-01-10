package org.theoliverlear.service;

import org.springframework.stereotype.Service;
import org.theoliverlear.model.Sudoku;
import org.theoliverlear.model.SudokuGenerator;

@Service
public class SudokuService {
    private SudokuGenerator sudokuGenerator = new SudokuGenerator();
    private Sudoku sudoku;

    public SudokuService() {
        this.sudokuGenerator.generateBoardRandomly();
        this.sudokuGenerator.getSudoku().getBoard().printBoard();
        this.sudoku = new Sudoku(this.sudokuGenerator.getMutedBoardFromBoard(5));
    }

    public String numToWord(int num) {
        return switch (num) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> throw new IllegalStateException("Unexpected value: " + num);
        };
    }
    //=============================-Getters-==================================
    public SudokuGenerator getSudokuGenerator() {
        return this.sudokuGenerator;
    }
    public Sudoku getSudoku() {
        return this.sudoku;
    }
    //=============================-Setters-==================================
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

}
