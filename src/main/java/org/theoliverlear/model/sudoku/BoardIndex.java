package org.theoliverlear.model.sudoku;

import java.util.Arrays;

public class BoardIndex {
    int rowIndex;
    int columnIndex;
    public BoardIndex(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }
    public BoardIndex(int[] index) {
        this.rowIndex = index[0];
        this.columnIndex = index[1];
    }
    public int getRowIndex() {
        return this.rowIndex;
    }
    public int getColumnIndex() {
        return this.columnIndex;
    }
    //=============================-Overrides-================================
    @Override
    public String toString() {
        return "Row: " + this.rowIndex + ", Column: " + this.columnIndex;
    }
    @Override
    public boolean equals(Object obj) {
        // If the object is null or not an instance of Board, then it is not
        // equal to this board.
        if (obj == null || !(obj instanceof BoardIndex)) {
            return false;
        }
        BoardIndex board = (BoardIndex) obj;
        return this.rowIndex == board.getRowIndex() &&
                this.columnIndex == board.getColumnIndex();
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] {this.rowIndex, this.columnIndex});
    }
}
