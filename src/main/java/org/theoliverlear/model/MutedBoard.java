package org.theoliverlear.model;

import java.util.ArrayList;

public class MutedBoard extends Board {

    public MutedBoard() {
        super();
    }
    ArrayList<BoardIndex> mutedIndices = new ArrayList<>();
    public MutedBoard(int[][] mutedBoard) {
        super(mutedBoard);
        this.fetchMutedIndices();
    }
    public MutedBoard(Board board) {
        super(board.getBoard());
        this.fetchMutedIndices();
    }
    public void fetchMutedIndices() {
        for (int row = 0; row < this.rowLength; row++) {
            for (int column = 0; column < this.colLength; column++) {
                if (this.board[row][column] != 0) {
                    this.muteBoardIndex(row + 1, column + 1);
                }
            }
        }
    }
    //=============================-Overrides-================================
    @Override
    public void placeNumber(int row, int column, int number) {
        int[] potentialPlaceIndex = {row - 1, column - 1};
        BoardIndex boardIndex = new BoardIndex(potentialPlaceIndex);
        if (!this.mutedIndices.contains(boardIndex)) {
            super.placeNumber(row, column, number);
        }
    }
    public void muteBoardIndex(int row, int column) {
        int[] muteIndex = {row - 1, column - 1};
        BoardIndex boardIndex = new BoardIndex(muteIndex);
        this.mutedIndices.add(boardIndex);
    }

    public ArrayList<BoardIndex> getMutedIndices() {
        return this.mutedIndices;
    }
}
