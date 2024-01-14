package org.theoliverlear.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.theoliverlear.entity.MutedBoard;
import org.theoliverlear.model.sudoku.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a Sudoku service that generates and handles Sudoku puzzles.
 */
@Service
public class SudokuService {
    //=============================-Variables-================================
    private static ArrayList<Difficulty> difficulties = new ArrayList<>(Arrays.asList(Difficulty.values()));
    private SudokuGenerator sudokuGenerator = new SudokuGenerator();
    private Sudoku sudoku;
    //=============================-Constants-================================
    private static final String MUTED_INDEX_STYLE = "background-color: #cccccc; " +
                                                    "border: 0.225vw solid black;" +
                                                    "width:98%; height:98%;";
    private static final String DEFAULT_STYLE = "width: 100%;" +
                                                "height: 100%;" +
                                                "display: flex;" +
                                                "justify-content: center;" +
                                                "align-items: center;" +
                                                "margin: 0;" +
                                                "padding: 0;" +
                                                "border: 0.0125vw solid black;" +
                                                "background-color: rgba(255, 255, 255, 0.9);" +
                                                "transition: background-color 0.15s;";
    private static final String RED_COLOR_CODE = "rgba(255, 0, 0, 0.5)";
    private static final String VIOLATING_STYLE = String.format("color: %s;" +
                                                                "font-weight: bold;" +
                                                                "border: 0.225vw solid %s; ",
                                                                RED_COLOR_CODE, RED_COLOR_CODE);
    /**
     * Constructs a SudokuService object with default difficulty level.
     * @see Sudoku
     * @see SudokuGenerator
     */
    //=============================-Constructors-=============================
    public SudokuService() {
        this.sudokuGenerator.generateBoardRandomly();
        this.sudokuGenerator.getSudoku().getBoard().printBoard();
        MutedBoard mutedBoardFromBoard = this.sudokuGenerator.getMutedBoardFromBoard(5);
        this.sudoku = new Sudoku(mutedBoardFromBoard);
    }

    /**
     * Constructs a SudokuService object with the given difficulty level.
     * @param difficulty the difficulty level of the Sudoku game
     *                  (BEGINNER, EASY, MEDIUM, HARD, or EXPERT)
     * @see Difficulty
     * @see Sudoku
     * @see SudokuGenerator
     */
    public SudokuService(Difficulty difficulty) {
        final int mutedIndices = difficulty.getMutedIndices();
        this.sudokuGenerator.generateBoardRandomly();
        this.sudokuGenerator.getSudoku().getBoard().printBoard();
        MutedBoard mutedBoardFromBoard = this.sudokuGenerator.getMutedBoardFromBoard(mutedIndices);
        this.sudoku = new Sudoku(mutedBoardFromBoard);
    }
    //=============================-Methods-==================================

    //---------------------------Number-To-Word-------------------------------

    /**
     * Converts a given integer to its corresponding word representation.
     * @param num the number to convert
     * @return the word representation of the number
     * @throws IllegalStateException if the given number is not within the
     * range of 1 to 9
     */
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
            default -> throw new IllegalStateException("Unexpected value: " +
                                                       num);
        };
    }
    //------------------------Get-Row-Col-From-Spot---------------------------
    /**
     * Retrieves the row and column values from a given spot string.
     * @param spot the spot string in the format "row-col"
     * @return an int array containing the row and column values
     */
    public int[] getRowColFromSpot(String spot) {
        String[] spotSplit = spot.split("-");
        int row = Integer.parseInt(spotSplit[0]);
        int col = Integer.parseInt(spotSplit[1]);
        return new int[] {row, col};
    }
    //----------------------------Is-Violating--------------------------------
    public boolean isViolating(SudokuGenerator sudokuGenerator, int rowIndex,
                               int colIndex, int tempValue) {
        boolean isViolating;
        if (tempValue == 0) {
            isViolating = false;
        } else {
            isViolating = sudokuGenerator.containsAny(rowIndex, colIndex,
                                                      tempValue);
        }
        return isViolating;
    }
    //---------------------------Get-Difficulty-------------------------------

    /**
     * Retrieves the Difficulty object based on the given difficulty string.
     * @param difficulty the difficulty word
     * @return the Difficulty as an enum object
     * @throws IllegalStateException if the given difficulty string is not recognized
     * @see Difficulty
     */
    public static Difficulty getDifficulty(String difficulty) {
        return switch (difficulty) {
            case "Beginner" -> difficulties.get(0);
            case "Easy" -> difficulties.get(1);
            case "Medium" -> difficulties.get(2);
            case "Hard" -> difficulties.get(3);
            case "Expert" -> difficulties.get(4);
            default -> throw new IllegalStateException("Unexpected value: " +
                                                       difficulty);
        };
    }

    //===========================-Render-Board-===============================

    //--------------------------Style-Muted-Indices---------------------------
    public void styleMutedIndices(Model model) {
        System.out.println("Styling muted indices");
        if (this.getSudoku().getBoard() instanceof MutedBoard mutedBoard) {
            ArrayList<BoardIndex> mutedIndices = mutedBoard.getMutedIndices();
            for (BoardIndex index : mutedIndices) {
                //--------------Row-Column-Equivalent-------------------------
                int row = index.getRowIndex() + 1;
                int column = index.getColumnIndex() + 1;
                //--------------Get-Id-For-Spot-Style-------------------------
                String spotLeftNum = this.numToWord(row);
                String spotRightNum = this.numToWord(column);
                String spotModelNum = spotLeftNum + "_" + spotRightNum + "_style";
                //-----------------Update-Page--------------------------------
                model.addAttribute(spotModelNum, MUTED_INDEX_STYLE);
            }
        }
    }
    //--------------------------Render-Board-Values---------------------------
    public void renderBoardValues(Model model) {
        System.out.println("Rendering board values");
        int[][] board = this.sudoku.getBoard().getBoard();
        //------------------------Get-Board-Lengths---------------------------
        int rowLength = this.sudoku.getBoard().getRowLength();
        int colLength = this.sudoku.getBoard().getColLength();
        //-----------------------Loop-Through-Board---------------------------
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            //-------------------Get-Row-Number-------------------------------
            String spotLeftNum = this.numToWord(rowIndex + 1);
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                //---------------Get-Column-Number----------------------------
                String spotRightNum = this.numToWord(colIndex + 1);
                //--------------Get-Id-For-Spot-Value-------------------------
                String spotModelNum = spotLeftNum + "_" + spotRightNum;
                String value = Integer.toString(board[rowIndex][colIndex]);
                if (value.equals("0")) {
                    value = "";
                }
                //-----------------Update-Page--------------------------------
                model.addAttribute(spotModelNum, value);
            }
        }
    }
    //----------------------------Render-Style--------------------------------
    public void renderStyle(Model model) {
        System.out.println("Rendering style");
        //------------------------Get-Board-Lengths---------------------------
        int rowLength = this.getSudoku().getBoard().getRowLength();
        int colLength = this.getSudoku().getBoard().getColLength();
        //-----------------------Loop-Through-Board---------------------------
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                //--------------Row-Column-Equivalent-------------------------
                int row = rowIndex + 1;
                int column = colIndex + 1;
                //-----------------Get-Current-Value-At-Spot------------------
                int tempValue = this.getSudoku().getBoard().getNumber(row, column);
                // Set the value to 0 to check if it the current value will
                // violate any rules.
                this.getSudoku().getBoard().placeNumber(row, column, 0);
                // Create a new SudokuGenerator object to check if the current
                // value will violate any rules without changing the current
                // board.
                SudokuGenerator sudokuGenerator = new SudokuGenerator();
                int[][] currentBoard = this.getSudoku().getBoard().getBoard();
                sudokuGenerator.getSudoku().getBoard().setBoard(currentBoard);
                sudokuGenerator.getSudoku().getBoard().placeNumber(row, column, 0);
                boolean isViolating = this.isViolating(sudokuGenerator,
                                                       rowIndex, colIndex,
                                                       tempValue);
                if (isViolating) {
                    //-----------------Update-Board---------------------------
                    sudokuGenerator.getSudoku().getBoard().placeNumber(row, column,
                                                                       tempValue);
                    this.sudoku.getBoard().placeNumber(row, column,
                                                            tempValue);
                    //--------------Get-Id-For-Spot-Style---------------------
                    String spotLeftNum = this.numToWord(row);
                    String spotRightNum = this.numToWord(column);
                    String spotModelNum = spotLeftNum + "_" + spotRightNum;
                    //-----------------Update-Page----------------------------
                    this.renderViolation(model, spotModelNum);
                } else {
                    //-----------------Update-Board---------------------------
                    sudokuGenerator.getSudoku().getBoard().placeNumber(row, column,
                                                                       tempValue);
                    this.sudoku.getBoard().placeNumber(row, column, tempValue);
                    //--------------Get-Id-For-Spot-Style---------------------
                    String spotLeftNum = this.numToWord(row);
                    String spotRightNum = this.numToWord(column);
                    String spotModelNum = spotLeftNum + "_" + spotRightNum + "_style";
                    //-----------------Update-Page----------------------------
                    model.addAttribute(spotModelNum, DEFAULT_STYLE);
                }
            }
        }
    }
    //----------------------------Render-Violation----------------------------
    /**
     * Renders the violation at a specific spot on the model.
     * @param model the model to render the violation on
     * @param spot  the spot where the violation occurred
     * @see SudokuService#renderStyle(Model)
     */
    public void renderViolation(Model model, String spot) {
        System.out.println("Rendering violation at spot " + spot);
        String spotModelNum = spot + "_style";
        model.addAttribute(spotModelNum, VIOLATING_STYLE);
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
