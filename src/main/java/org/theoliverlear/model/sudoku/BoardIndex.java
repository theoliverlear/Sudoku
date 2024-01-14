package org.theoliverlear.model.sudoku;

import java.util.Arrays;

public class BoardIndex {
    //============================-Variables-=================================
    int rowIndex;
    int columnIndex;
    //===========================-Constructors-===============================
    public BoardIndex(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }
    public BoardIndex(int[] index) {
        this.rowIndex = index[0];
        this.columnIndex = index[1];
    }

    //=============================-Overrides-================================

    //------------------------------To-String---------------------------------
    @Override
    public String toString() {
        return "Row: " + this.rowIndex + ", Column: " + this.columnIndex;
    }
    //-------------------------------Equals-----------------------------------
    @Override
    public boolean equals(Object obj) {
        // If the object is null or not an instance of Board, then it is not
        // equal to this board.
        if (obj == null || !(obj instanceof BoardIndex)) {
            return false;
        }
        BoardIndex board = (BoardIndex) obj;
        return this.rowIndex == board.rowIndex &&
                this.columnIndex == board.columnIndex;
    }
    //-----------------------------Hashcode-----------------------------------
    @Override
    public int hashCode() {
        return Arrays.hashCode(new int[] {this.rowIndex, this.columnIndex});
    }
    //=============================-Getters-==================================
    public int getRowIndex() {
        return this.rowIndex;
    }
    public int getColumnIndex() {
        return this.columnIndex;
    }
}
