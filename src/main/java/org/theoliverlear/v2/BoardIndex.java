package org.theoliverlear.v2;

public class BoardIndex {
    int rowIndex;
    int columnIndex;
    public BoardIndex(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }
    public int getRowIndex() {
        return this.rowIndex;
    }
    public int getColumnIndex() {
        return this.columnIndex;
    }
    public boolean equals(BoardIndex other) {
        return this.rowIndex == other.getRowIndex() && this.columnIndex == other.getColumnIndex();
    }
    public String toString() {
        return "Row: " + this.rowIndex + ", Column: " + this.columnIndex;
    }

}
