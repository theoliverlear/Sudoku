package org.theoliverlear;

public class Sudoku {
    Board board;
    int moves;
    boolean hasWon;

    public Sudoku() {
        this.board = new Board();
        this.moves = 0;
        this.hasWon = false;
    }
    public void playSudoku() {
        while (!this.hasWon) {
            this.board.printBoard();
            this.board.makeMove();
            this.moves++;
            this.board.checkAnnouncements();
            this.determineResult();
        }
    }
    public void determineResult() {
        this.hasWon = this.board.isWinningBoard();
    }
    public void announceWinner() {
        System.out.printf("Congratulations! You won in %d moves!",
                          this.moves);
    }
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.playSudoku();
    }
    public Board getBoard() {
        return this.board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
}
