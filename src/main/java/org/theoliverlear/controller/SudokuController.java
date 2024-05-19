package org.theoliverlear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.comm.timer.TimerRequest;
import org.theoliverlear.entity.Board;
import org.theoliverlear.entity.Timer;
import org.theoliverlear.entity.BoardIndex;
import org.theoliverlear.model.sudoku.Difficulty;
import org.theoliverlear.service.SudokuService;
import java.util.ArrayList;

@Controller
public class SudokuController {
    //=============================-Variables-================================
    @Autowired
    SudokuService sudokuService;
    Difficulty currentDifficulty = Difficulty.BEGINNER;
    //------------------------------Home-Page---------------------------------
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    //---------------------------Start-New-Game-------------------------------
    @RequestMapping("/new-game")
    public String reset(Model model) {
        //----------------------Reset-Winning-Board---------------------------
        this.sudokuService.getSudokuGenerator().getSudoku().getBoard()
                                                           .resetBoard();
        //-------------------Generate-New-Winning-Board-----------------------
        this.sudokuService.getSudokuGenerator().generateBoardRandomly();
        //-----------------------Print-Winning-Board--------------------------
        this.sudokuService.getSudokuGenerator().getSudoku().getBoard()
                                                           .printBoard();
        //-----------------------Reset-Player-Board---------------------------
        this.sudokuService.getSudoku().getBoard().resetBoard();
        //-------------------------Build-New-Board----------------------------
        int mutedIndices = this.currentDifficulty.getMutedIndices();
        Board mutedBoardFromBoard = this.sudokuService.getSudokuGenerator()
                                                      .getStarterBoardFromBoard(mutedIndices);
        mutedBoardFromBoard.setDifficulty(this.currentDifficulty.getName());
        System.out.println("Muted Indices from generator: " + mutedBoardFromBoard.getMutedIndices());
        System.out.println("-----------------Muted-Board-From-Board-----------------");
        mutedBoardFromBoard.printBoard();
        this.sudokuService.getSudoku().setBoard(mutedBoardFromBoard);
        System.out.println("Muted Indices after being set: " + this.sudokuService.getSudoku().getBoard().getMutedIndices());
        this.sudokuService.getSudoku().getBoard().setTimer(new Timer().getTime());
        this.sudokuService.getSudoku().getBoard().printBoard();
        //--------------------------Render-Board------------------------------
        this.sudokuService.renderBoardValues(model);
        this.sudokuService.renderStyle(model);
        this.sudokuService.styleMutedIndices(model);
        //--------------------------Return-Patch------------------------------
        return "game-board-patch :: game-board-patch";
    }
    //---------------------------Make-Spot-Choice-----------------------------
    @RequestMapping("/spot/{spot}/{value}")
    public String spot(@PathVariable String spot, @PathVariable String value,
                       Model model) {
        this.sudokuService.styleMutedIndices(model);
        this.sudokuService.renderBoardValues(model);
        System.out.println("Spot: " + spot + " Value: " + value);
        int[] spotSplit = this.sudokuService.getRowColFromSpot(spot);
        int row = spotSplit[0];
        int col = spotSplit[1];
        int val;
        //-------------------------Convert-Null-Value-------------------------
        // If the value is a space escape code or 0, convert it to 0 so that
        // the board can read it as an empty spot.
        if (value.equals("0") || value.equals(" ")) {
            val = 0;
        } else {
            val = Integer.parseInt(value);
        }
        //--------------------------Build-Id-String---------------------------
        String spotLeftNum = this.sudokuService.numToWord(row);
        String spotRightNum = this.sudokuService.numToWord(col);
        String spotModelNum = spotLeftNum + "_" + spotRightNum;
        //--------------------------Is-Spot-Muted-----------------------------
        // Determine if indices are muted by retrieving board's array of muted
        // indices and validating in a loop.
        ArrayList<BoardIndex> mutedBoardIndices = this.sudokuService.getSudoku()
                                                                    .getBoard()
                                                                    .getMutedIndices();
        boolean isMuted = false;
        // If the spot is muted, do not render the number.
        for (BoardIndex index : mutedBoardIndices) {
            if (index.getRowIndex() == row - 1 && index.getColumnIndex() == col - 1) {
                isMuted = true;
                break;
            }
        }
        //----------------------Render-Spot-Numbers---------------------------
        if (!isMuted) {
            this.sudokuService.getSudoku().getBoard().placeNumber(row, col, val);

            model.addAttribute(spotModelNum, value);
        }
        //--------------------------Render-Board------------------------------
        this.sudokuService.renderStyle(model);
        this.sudokuService.styleMutedIndices(model);
        this.sudokuService.getSudoku().getBoard().printBoard();
        //-------------------------Return-Patch------------------------------
        return "game-board-patch :: game-board-patch";
    }
    //----------------------------Is-Spot-Muted-------------------------------
    @RequestMapping("/muted/{spot}")
    public ResponseEntity<String> isSpotMuted(@PathVariable String spot) {
        // Split the spot string into row and column numbers. This will be
        // used to evaluate if the spot is muted.
        int[] spotSplit = this.sudokuService.getRowColFromSpot(spot);
        int row = spotSplit[0];
        int col = spotSplit[1];
        // Determine if indices are muted by retrieving board's array of muted
        // indices and validating in a loop.
        ArrayList<BoardIndex> mutedBoardIndices = this.sudokuService.getSudoku()
                                                                    .getBoard()
                                                                    .getMutedIndices();
        System.out.println("Muted indices from muted request: " + mutedBoardIndices);
        // For each muted index, check if the row and column match the spot.
        // If it matches, it is a member of the muted board and is muted. A
        // response of true is returned.
        for (BoardIndex index : mutedBoardIndices) {
            if (index.getRowIndex() == row - 1 && index.getColumnIndex() == col - 1) {
                System.out.println("Spot " + spot + " is muted");
                return new ResponseEntity<>("true", HttpStatus.OK);
            }
        }
        // If none of the spots match, the spot is not muted. A response of
        // false is returned.
        System.out.println("Spot " + spot + " is not muted");
        return new ResponseEntity<>("false", HttpStatus.OK);
    }
    //--------------------------Is-Winning-Board------------------------------
    @RequestMapping("/check")
    public ResponseEntity<String> check() {
        // Get the current board and check if it is a winning board.
        Board currentBoard = this.sudokuService.getSudoku().getBoard();
        boolean isWinningBoard = currentBoard.isWinningBoard();
        // If it is a winning board, return true. If it is not a winning
        // board, return false.
        if (isWinningBoard) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.OK);
    }
    //---------------------------Choose-Difficulty----------------------------
    @RequestMapping("/difficulty/{difficulty}")
    public ResponseEntity<String> setDifficulty(@PathVariable String difficulty) {
        System.out.println("Chosen Difficulty: " + difficulty);
        // Trim the difficulty string and remove any extra characters
        // including spaces and newlines.
        difficulty = difficulty.trim()
                               .replaceAll("\\r", "")
                               .replaceAll("\\n", "");
        // Parse the difficulty string into a Difficulty enum.
        Difficulty difficultyLevel = SudokuService.getDifficulty(difficulty);
        // Set the current difficulty to the parsed difficulty.
        this.currentDifficulty = difficultyLevel;
        this.sudokuService.getSudoku().getBoard().setDifficulty(difficultyLevel.getName());
        // Return the difficulty level and an OK status to indicate that the
        // method was fully successful.
        return new ResponseEntity<>("Difficulty: " + difficulty, HttpStatus.OK);
    }
    //--------------------------Get-Time-Elapsed------------------------------
    @RequestMapping("/time")
    public ResponseEntity<String> setTimeElapsed(@RequestBody TimerRequest timerRequest) {
        Timer timer = new Timer(timerRequest.getTimer());
        this.sudokuService.getSudoku().getBoard().setTimer(timer.getTime());
        return new ResponseEntity<>("Time: " + timer.getTime(), HttpStatus.OK);
    }
}
