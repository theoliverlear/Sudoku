package org.theoliverlear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.model.BoardIndex;
import org.theoliverlear.model.MutedBoard;
import org.theoliverlear.model.SudokuGenerator;
import org.theoliverlear.service.SudokuService;

import java.util.ArrayList;


@Controller
public class SudokuController {
    @Autowired
    SudokuService sudokuService = new SudokuService();
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/new-game")
    public String reset(Model model) {
        this.sudokuService.getSudokuGenerator().getSudoku().getBoard().resetBoard();
        this.sudokuService.getSudokuGenerator().generateBoardRandomly();
        this.sudokuService.getSudokuGenerator().getSudoku().getBoard().printBoard();

        this.sudokuService.getSudoku().getBoard().resetBoard();
        MutedBoard mutedBoardFromBoard = this.sudokuService.getSudokuGenerator().getMutedBoardFromBoard(75);
        this.sudokuService.getSudoku().setBoard(mutedBoardFromBoard);


        this.renderBoardValues(model);
        this.renderStyle(model);
        this.styleMutedIndices(model);

        return "game-board-patch :: game-board-patch";
    }

    @RequestMapping("/spot/{spot}/{value}")
    public String spot(@PathVariable String spot, @PathVariable String value,
                       Model model) {
        this.styleMutedIndices(model);
        this.renderBoardValues(model);

        String[] spotSplit = spot.split("-");
        int row = Integer.parseInt(spotSplit[0]);
        int col = Integer.parseInt(spotSplit[1]);
        int val = Integer.parseInt(value);

        String spotLeftNum = this.sudokuService.numToWord(row);
        String spotRightNum = this.sudokuService.numToWord(col);
        String spotModelNum = spotLeftNum + "_" + spotRightNum;

        MutedBoard currentBoard = (MutedBoard) this.sudokuService.getSudoku().getBoard();
        ArrayList<BoardIndex> mutedBoardIndices = currentBoard.getMutedBoardIndices();
        boolean isMuted = false;
        for (BoardIndex index : mutedBoardIndices) {
            if (index.getRowIndex() == row - 1 && index.getColumnIndex() == col - 1) {
                isMuted = true;
            }
        }
        if (!isMuted) {
            this.sudokuService.getSudoku().getBoard().placeNumber(row, col, val);
            model.addAttribute(spotModelNum, value);
        }
        this.renderStyle(model);
        this.styleMutedIndices(model);
        this.sudokuService.getSudoku().getBoard().printBoard();
        return "game-board-patch :: game-board-patch";
    }

    public void styleMutedIndices(Model model) {
        System.out.println("Styling muted indices");
        final String MUTED_INDEX_STYLE = "background-color: #cccccc; " +
                                         "border: 0.225vw solid black;" +
                                         "width:98%; height:98%;";
        if (this.sudokuService.getSudoku().getBoard() instanceof MutedBoard mutedBoard) {
            ArrayList<BoardIndex> mutedIndices = mutedBoard.getMutedBoardIndices();
            for (BoardIndex index : mutedIndices) {
                int row = index.getRowIndex() + 1;
                int column = index.getColumnIndex() + 1;
                String spotLeftNum = this.sudokuService.numToWord(row);
                String spotRightNum = this.sudokuService.numToWord(column);
                String spotModelNum = spotLeftNum + "_" + spotRightNum + "_style";
                model.addAttribute(spotModelNum, MUTED_INDEX_STYLE);
            }
        }
    }
    public void renderBoardValues(Model model) {
        System.out.println("Rendering board values");
        int[][] board = this.sudokuService.getSudoku().getBoard().getBoard();
        for (int row = 0; row < board.length; row++) {
            String spotLeftNum = this.sudokuService.numToWord(row + 1);
            for (int col = 0; col < board[row].length; col++) {
                String spotRightNum = this.sudokuService.numToWord(col + 1);
                String spotModelNum = spotLeftNum + "_" + spotRightNum;
                String value = Integer.toString(board[row][col]);
                if (value.equals("0")) {
                    value = "";
                }
                model.addAttribute(spotModelNum, value);
            }
        }
    }
    public void renderStyle(Model model) {
        System.out.println("Rendering style");
        final String DEFAULT_STYLE = "width: 100%;" +
                                     "height: 100%;" +
                                     "display: flex;" +
                                     "justify-content: center;" +
                                     "align-items: center;" +
                                     "margin: 0;" +
                                     "padding: 0;" +
                                     "border: 0.0125vw solid black;" +
                                     "background-color: rgba(255, 255, 255, 0.9);" +
                                     "transition: background-color 0.15s;";
        int rowLength = this.sudokuService.getSudoku().getBoard().getRowLength();
        int colLength = this.sudokuService.getSudoku().getBoard().getColLength();
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int colIndex = 0; colIndex < colLength; colIndex++) {
                int row = rowIndex + 1;
                int column = colIndex + 1;
                int tempValue = this.sudokuService.getSudoku().getBoard().getNumber(row, column);
                this.sudokuService.getSudoku().getBoard().placeNumber(row, column, 0);
                SudokuGenerator sudokuGenerator = new SudokuGenerator();
                int[][] currentBoard = this.sudokuService.getSudoku().getBoard().getBoard();
                sudokuGenerator.getSudoku().getBoard().setBoard(currentBoard);
                sudokuGenerator.getSudoku().getBoard().placeNumber(row, column, 0);
                boolean isViolating;
                if (tempValue == 0) {
                    isViolating = false;
                } else {
                    isViolating = sudokuGenerator.containsAny(rowIndex, colIndex, tempValue);
                }
                if (isViolating) {
                    this.sudokuService.getSudoku().getBoard().placeNumber(row, column, tempValue);
                    sudokuGenerator.getSudoku().getBoard().placeNumber(row, column, tempValue);
                    String spotLeftNum = this.sudokuService.numToWord(row);
                    String spotRightNum = this.sudokuService.numToWord(column);
                    String spotModelNum = spotLeftNum + "_" + spotRightNum;
                    this.renderViolation(model, spotModelNum);
                } else {
                    this.sudokuService.getSudoku().getBoard().placeNumber(row, column, tempValue);
                    sudokuGenerator.getSudoku().getBoard().placeNumber(row, column, tempValue);
                    String spotLeftNum = this.sudokuService.numToWord(row);
                    String spotRightNum = this.sudokuService.numToWord(column);
                    String spotModelNum = spotLeftNum + "_" + spotRightNum + "_style";
                    model.addAttribute(spotModelNum, DEFAULT_STYLE);
                }
            }
        }
    }
    public void renderViolation(Model model, String spot) {
        System.out.println("Rendering violation at spot " + spot);
        final String RED_COLOR_CODE = "rgba(255, 0, 0, 0.5)";
        final String VIOLATING_STYLE = String.format("color: %s;" +
                                                     "font-weight: bold;" +
                                                     "border: 0.225vw solid %s; ",
                                                     RED_COLOR_CODE, RED_COLOR_CODE);
        String spotModelNum = spot + "_style";
        model.addAttribute(spotModelNum, VIOLATING_STYLE);
    }
    @RequestMapping("/muted/{spot}")
    public ResponseEntity<String> isSpotMuted(@PathVariable String spot) {
        String[] spotSplit = spot.split("-");
        int row = Integer.parseInt(spotSplit[0]);
        int col = Integer.parseInt(spotSplit[1]);

        ArrayList<BoardIndex> mutedBoardIndices = ((MutedBoard) this.sudokuService.getSudoku().getBoard()).getMutedBoardIndices();
        for (BoardIndex index : mutedBoardIndices) {
            if (index.getRowIndex() == row - 1 && index.getColumnIndex() == col - 1) {
                System.out.println("Spot " + spot + " is muted");
                return new ResponseEntity<>("true", HttpStatus.OK);
            }
        }
        System.out.println("Spot " + spot + " is not muted");
        return new ResponseEntity<>("false", HttpStatus.OK);
    }
}
