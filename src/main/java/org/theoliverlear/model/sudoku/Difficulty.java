package org.theoliverlear.model.sudoku;

/**
 * Enumeration class representing different difficulty levels in a Sudoku game.
 */
public enum Difficulty {
    BEGINNER(60, "Beginner"),
    EASY(45, "Easy"),
    MEDIUM(30, "Medium"),
    HARD(20, "Hard"),
    EXPERT(10, "Expert");
    //=============================-Variables-================================
    final int mutedIndices;
    final String name;
    //===========================-Constructors-===============================
    Difficulty(int mutedIndices, String name) {
        this.mutedIndices = mutedIndices;
        this.name = name;
    }
    //=============================-Getters-==================================
    public int getMutedIndices() {
        return this.mutedIndices;
    }
    public String getName() {
        return this.name;
    }
}
