package org.theoliverlear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.theoliverlear.model.Board;
import org.theoliverlear.model.BoardIndex;
import org.theoliverlear.model.Difficulty;
import org.theoliverlear.model.MutedBoard;
import org.theoliverlear.service.SudokuService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class SudokuController {
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
        MutedBoard mutedBoardFromBoard = this.sudokuService
                                             .getSudokuGenerator()
                                             .getMutedBoardFromBoard(mutedIndices);
        this.sudokuService.getSudoku().setBoard(mutedBoardFromBoard);
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

        MutedBoard currentBoard = (MutedBoard) this.sudokuService.getSudoku()
                                                                 .getBoard();
        ArrayList<BoardIndex> mutedBoardIndices = currentBoard.getMutedIndices();
        boolean isMuted = false;
        for (BoardIndex index : mutedBoardIndices) {
            if (index.getRowIndex() == row - 1 && index.getColumnIndex() == col - 1) {
                isMuted = true;
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
        int[] spotSplit = this.sudokuService.getRowColFromSpot(spot);
        int row = spotSplit[0];
        int col = spotSplit[1];
        MutedBoard mutedBoard = (MutedBoard) this.sudokuService.getSudoku().getBoard();
        ArrayList<BoardIndex> mutedBoardIndices = mutedBoard.getMutedIndices();
        for (BoardIndex index : mutedBoardIndices) {
            if (index.getRowIndex() == row - 1 && index.getColumnIndex() == col - 1) {
                System.out.println("Spot " + spot + " is muted");
                return new ResponseEntity<>("true", HttpStatus.OK);
            }
        }
        System.out.println("Spot " + spot + " is not muted");
        return new ResponseEntity<>("false", HttpStatus.OK);
    }
    //--------------------------Is-Winning-Board------------------------------
    @RequestMapping("/check")
    public ResponseEntity<String> check() {
        Board currentBoard = this.sudokuService.getSudoku().getBoard();
        boolean isWinningBoard = currentBoard.isWinningBoard();
        if (isWinningBoard) {
            return new ResponseEntity<>("true", HttpStatus.OK);
        }
        return new ResponseEntity<>("false", HttpStatus.OK);
    }
    //---------------------------Choose-Difficulty----------------------------
    @RequestMapping("/difficulty/{difficulty}")
    public ResponseEntity<String> setDifficulty(@PathVariable String difficulty) {
        System.out.println("Chosen Difficulty: " + difficulty);
        difficulty = difficulty.trim().replaceAll("\\r", "").replaceAll("\\n", "");
        Difficulty difficultyLevel = SudokuService.getDifficulty(difficulty);
        this.currentDifficulty = difficultyLevel;
        return new ResponseEntity<>("difficulty: " + difficulty, HttpStatus.OK);
    }
}
