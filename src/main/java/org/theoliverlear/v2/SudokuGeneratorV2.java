package org.theoliverlear.v2;

import java.util.*;

public class SudokuGeneratorV2 {
    Sudoku sudoku;
    final int rowLength;
    final int colLength;
    HashMap<Board, HashSet<Integer>> numsTriedAtBoard;
    //===========================-Constructors-===============================
    public SudokuGeneratorV2() {
        this.sudoku = new Sudoku();
        this.rowLength = this.sudoku.getBoard().getRowLength();
        this.colLength = this.sudoku.getBoard().getColLength();
        this.numsTriedAtBoard = new HashMap<>();
    }
    public SudokuGeneratorV2(MutedBoard mutedBoard) {
        this.sudoku = new Sudoku(mutedBoard);
        this.rowLength = this.sudoku.getBoard().getRowLength();
        this.colLength = this.sudoku.getBoard().getColLength();
        this.numsTriedAtBoard = new HashMap<>();
    }
    // TODO: Pass a GeneratorGUI object to this method. This will allow us to
    //       update the GUI with the current board.
    public void generateBoard() {
        // This method attempts to generate a complete sudoku board by
        // implementing a backtracking algorithm. Note that most methods are
        // index-based, meaning that they start from zero, but some methods
        // like the placeNumber method are number-based, meaning that they
        // start from one. This is because the placeNumber method is meant to
        // be used by the user, and the user will not be using zero-based
        // numbers.

        // Check whether all available numbers are already in a row, column,
        // or square.

        // If it is, backtrack to the previous spot and try a different
        // number.

        // If it is not, try a number from 1 to 9 in the given spot and
        // continue to the next spot.

        // Check if the number is already in row, column, or square.

        // If it is, try next number.

        // First loop is for the row. Second loop is for the column.
        // Third loop is for the number.


        int number = 1;
        int currentFailingNum = -1;
        for (int rowIndex = 0; rowIndex < this.rowLength;) {
            for (int colIndex = 0; colIndex < this.colLength;) {
                // At this point in the loop, we have an index for the number.

                int[] currentIndex = {rowIndex, colIndex};

                // We have a board state that we can check previous states
                // against. This means that we can check to see if we have
                // already tried all numbers at this board state.

                int[][] currentBoard = this.sudoku.getBoard().getBoard();

                // We know we have tried numbers at this board state if the
                // numsTriedAtBoard HashMap contains the board state as a key.

                // If the board state is in the HashMap, then we can assume
                // that we have tried numbers at this board state. We get the
                // HashSet of numbers that have been tried at this board to
                // check use in the allNumbersContainsAny method which handles
                // failed placements.

                HashSet<Integer> numsTried = this.getNumsTriedAtBoard(new Board(currentBoard));
                if (numsTried != null) {
                    if (numsTried.isEmpty()) {
                        System.out.println("Empty");
                    } else {
                        numsTried.forEach(System.out::println);
                    }
                }
                System.out.println("Boards: " + this.numsTriedAtBoard.size());
                // If the board state is not in the HashMap, then we can
                // assume that we have not tried any numbers at this board
                // state. We can then add the board state to the HashMap and
                // add the number to the HashSet at the key of the board.



                // We can now use this index to check if all numbers fail.
                // This may include numbers that have already been attempted
                // successfully but cause a failure at a future index. To
                // account for this, we need to keep track of the numbers
                // that have been tried at each board state. The numbers in
                // the numsTried set will be included as failed numbers in
                // the allNumbersContainsAny method.

                // If all numbers fail, we need to backtrack.
                if (this.allNumbersContainsAny(rowIndex, colIndex, numsTried)) {


                    // If a number fails at this index, we need to reevaluate
                    // the previous indexes. This means that we need to
                    // backtrack to the previous index and try a different
                    // number. A problem arises when we try to backtrack to
                    // a number that has already been backtracked to. This
                    // means that we need to try a different number at the
                    // previous index. This is why we need to keep track of
                    // the numbers tried at each board state.


                    System.out.println("Backtracking");
                    // Backtracking occurs in a failed placement.

                    // If we are needing to backtrack, we need to remove the
                    // number at the current row and column. This is so that
                    // we can try to reimplement the number loop without
                    // being constrained by the number that previously may
                    // have caused a failure.

                    // The current index is an index that was advanced upon by
                    // an unsuccessful predecessor. This means that we need
                    // to remove the number at the current index before
                    // setting the index to the previous index.

                    this.sudoku.getBoard().placeNumber(rowIndex + 1, colIndex + 1, 0);

                    // If we try to backtrack past the first row, we have an
                    // invalid board and we can return.

                    if (rowIndex == 0 && colIndex == 0) {
                        return;
                    }

                    // A column is decremented in every failed placement
                    // except for when it is at the first column. In that
                    // case, the column is set to 8 and the row is
                    // decremented.

                    if (colIndex == 0) {
                        colIndex = 8;
                        rowIndex--;
                    } else {
                        colIndex--;
                    }

                    // We need to get the number that we need to add to the
                    // list of failed numbers. This number is the number that
                    // we are backtracking from. When we inevitably try to
                    // come back to this number, we need to know that we have
                    // already tried it. This is why we need to add it to the
                    // list of failed numbers.

                    // We need to get the number that we are backtracking and
                    // add it to the list of failed numbers.

                    currentFailingNum = this.sudoku.getBoard().getNumber(rowIndex + 1, colIndex + 1);
//                    currentBoard = this.sudoku.getBoard().getBoard();
//                    this.addNumToNumsTriedAtBoard(new Board(currentBoard), currentFailingNum);

                    // We set number to the number that we need to increment
                    // to. This is so that we can try to reevaluate the
                    // number's value without beginning the failed number
                    // again.

                    number = this.getNextNumber(currentFailingNum);

                    // We will need to replace the number at the backtracked
                    // placement to 0. This is so that we can try and
                    // reevaluate the number's value again.
                    this.sudoku.getBoard().placeNumber(rowIndex + 1, colIndex + 1, 0);

                    currentBoard = this.sudoku.getBoard().getBoard();
                    this.addNumToNumsTriedAtBoard(new Board(currentBoard), currentFailingNum);

                    // We print the board to see the progress.
                    this.sudoku.getBoard().printBoard();
                    System.out.println();
                } else {
                    // If all numbers do not fail, we can move on to the next
                    // loop to determine the number to place.
                    for (int tries = 0; tries < 9; tries++) {

                        // Here we can check if a number is valid knowing that at
                        // least one number is valid.

                        if (!this.containsAny(rowIndex, colIndex, number)) {
                            // Once knowing the number, we assign it to the board at
                            // the current row and column.
                            this.sudoku.getBoard().placeNumber(rowIndex + 1, colIndex + 1, number);

                            // Advancement occurs in a successful placement.

                            // If we try to advance past the last row, we have
                            // a completed board and we can return.
                            if (rowIndex == 8 && colIndex == 8) {
                                return;
                            }

                            // A column increment occurs in every successful
                            // placement except for when it is at the last
                            // column. In that case, the column is reset to 0
                            // and the row is incremented.

                            if (colIndex == 8) {
                                colIndex = 0;
                                rowIndex++;
                            } else {
                                colIndex++;
                            }
                            // We can now set the number to 1 to start the
                            // process over again.
                            //number = 1;
                            number = this.getNextNumber(number);

                            // We print the board to see the progress.
                            this.sudoku.getBoard().printBoard();
                            System.out.println();

                            // Advancement could mean that we will try to
                            // place a number in a row that already contains
                            // a number. If this is the case, we need to
                            // break out of the number loop and try to
                            // evaluate if our next placement will be valid.
                            break;
                        } else {
                            // If the number is not valid, we increment the
                            // number to find the eventual valid number.
                            number = this.getNextNumber(number);
                        }
                    }
                }
            }
        }
    }
    public void generateBoardRandomly() {
        // This method attempts to generate a complete sudoku board by
        // implementing a backtracking algorithm. Note that most methods are
        // index-based, meaning that they start from zero, but some methods
        // like the placeNumber method are number-based, meaning that they
        // start from one. This is because the placeNumber method is meant to
        // be used by the user, and the user will not be using zero-based
        // numbers.

        // Check whether all available numbers are already in a row, column,
        // or square.

        // If it is, backtrack to the previous spot and try a different
        // number.

        // If it is not, try a number from 1 to 9 in the given spot and
        // continue to the next spot.

        // Check if the number is already in row, column, or square.

        // If it is, try next number.

        // First loop is for the row. Second loop is for the column.
        // Third loop is for the number.


//        int number = 1;
        int number = new Random().nextInt(9) + 1;
        int currentFailingNum = -1;
        for (int rowIndex = 0; rowIndex < this.rowLength;) {
            for (int colIndex = 0; colIndex < this.colLength;) {
                // At this point in the loop, we have an index for the number.

                int[] currentIndex = {rowIndex, colIndex};

                // We have a board state that we can check previous states
                // against. This means that we can check to see if we have
                // already tried all numbers at this board state.

                int[][] currentBoard = this.sudoku.getBoard().getBoard();

                // We know we have tried numbers at this board state if the
                // numsTriedAtBoard HashMap contains the board state as a key.

                // If the board state is in the HashMap, then we can assume
                // that we have tried numbers at this board state. We get the
                // HashSet of numbers that have been tried at this board to
                // check use in the allNumbersContainsAny method which handles
                // failed placements.

                HashSet<Integer> numsTried = this.getNumsTriedAtBoard(new Board(currentBoard));
                if (numsTried != null) {
                    if (numsTried.isEmpty()) {
                        System.out.println("Empty");
                    } else {
                        numsTried.forEach(System.out::println);
                    }
                }
                System.out.println("Boards: " + this.numsTriedAtBoard.size());
                // If the board state is not in the HashMap, then we can
                // assume that we have not tried any numbers at this board
                // state. We can then add the board state to the HashMap and
                // add the number to the HashSet at the key of the board.



                // We can now use this index to check if all numbers fail.
                // This may include numbers that have already been attempted
                // successfully but cause a failure at a future index. To
                // account for this, we need to keep track of the numbers
                // that have been tried at each board state. The numbers in
                // the numsTried set will be included as failed numbers in
                // the allNumbersContainsAny method.

                // If all numbers fail, we need to backtrack.
                if (this.allNumbersContainsAny(rowIndex, colIndex, numsTried)) {


                    // If a number fails at this index, we need to reevaluate
                    // the previous indexes. This means that we need to
                    // backtrack to the previous index and try a different
                    // number. A problem arises when we try to backtrack to
                    // a number that has already been backtracked to. This
                    // means that we need to try a different number at the
                    // previous index. This is why we need to keep track of
                    // the numbers tried at each board state.


                    System.out.println("Backtracking");
                    // Backtracking occurs in a failed placement.

                    // If we are needing to backtrack, we need to remove the
                    // number at the current row and column. This is so that
                    // we can try to reimplement the number loop without
                    // being constrained by the number that previously may
                    // have caused a failure.

                    // The current index is an index that was advanced upon by
                    // an unsuccessful predecessor. This means that we need
                    // to remove the number at the current index before
                    // setting the index to the previous index.

                    this.sudoku.getBoard().placeNumber(rowIndex + 1, colIndex + 1, 0);

                    // If we try to backtrack past the first row, we have an
                    // invalid board and we can return.

                    if (rowIndex == 0 && colIndex == 0) {
                        return;
                    }

                    // A column is decremented in every failed placement
                    // except for when it is at the first column. In that
                    // case, the column is set to 8 and the row is
                    // decremented.

                    if (colIndex == 0) {
                        colIndex = 8;
                        rowIndex--;
                    } else {
                        colIndex--;
                    }

                    // We need to get the number that we need to add to the
                    // list of failed numbers. This number is the number that
                    // we are backtracking from. When we inevitably try to
                    // come back to this number, we need to know that we have
                    // already tried it. This is why we need to add it to the
                    // list of failed numbers.

                    // We need to get the number that we are backtracking and
                    // add it to the list of failed numbers.

                    currentFailingNum = this.sudoku.getBoard().getNumber(rowIndex + 1, colIndex + 1);
//                    currentBoard = this.sudoku.getBoard().getBoard();
//                    this.addNumToNumsTriedAtBoard(new Board(currentBoard), currentFailingNum);

                    // We set number to the number that we need to increment
                    // to. This is so that we can try to reevaluate the
                    // number's value without beginning the failed number
                    // again.

                    // number = this.getNextNumber(currentFailingNum);

                    if (numsTried == null) {
                        numsTried = new HashSet<>();
                    }
                    number = this.getNextNumber(numsTried);

                    // We will need to replace the number at the backtracked
                    // placement to 0. This is so that we can try and
                    // reevaluate the number's value again.
                    this.sudoku.getBoard().placeNumber(rowIndex + 1, colIndex + 1, 0);

                    currentBoard = this.sudoku.getBoard().getBoard();
                    this.addNumToNumsTriedAtBoard(new Board(currentBoard), currentFailingNum);

                    // We print the board to see the progress.
                    this.sudoku.getBoard().printBoard();
                    System.out.println();
                } else {
                    // If all numbers do not fail, we can move on to the next
                    // loop to determine the number to place.


                    for (int tries = 0; tries < 9; tries++) {

                        // Here we can check if a number is valid knowing that at
                        // least one number is valid.

                        if (!this.containsAny(rowIndex, colIndex, number)) {
                            // Once knowing the number, we assign it to the board at
                            // the current row and column.
                            this.sudoku.getBoard().placeNumber(rowIndex + 1, colIndex + 1, number);

                            // Advancement occurs in a successful placement.

                            // If we try to advance past the last row, we have
                            // a completed board and we can return.
                            if (rowIndex == 8 && colIndex == 8) {
                                return;
                            }

                            // A column increment occurs in every successful
                            // placement except for when it is at the last
                            // column. In that case, the column is reset to 0
                            // and the row is incremented.

                            if (colIndex == 8) {
                                colIndex = 0;
                                rowIndex++;
                            } else {
                                colIndex++;
                            }
                            // We can now set the number to 1 to start the
                            // process over again.
                            //number = 1;

                            if (numsTried == null) {
                                numsTried = new HashSet<>();
                            }
                            number = this.getNextNumber(numsTried);

                            // We print the board to see the progress.
                            this.sudoku.getBoard().printBoard();
                            System.out.println();

                            // Advancement could mean that we will try to
                            // place a number in a row that already contains
                            // a number. If this is the case, we need to
                            // break out of the number loop and try to
                            // evaluate if our next placement will be valid.
                            break;
                        } else {
                            // If the number is not valid, we increment the
                            // number to find the eventual valid number.
                            number = this.getNextNumber(number);
                        }
                    }
                }
            }
        }
    }


    /**
     * This method checks if all numbers have been tried on a given board. If
     * all numbers have been tried, then the method returns true. Otherwise,
     * the method returns false.
     */

    public HashSet<Integer> getNumsTriedAtBoard(Board board) {
        // This method returns the HashSet of numbers that have been tried on
        // a given board. If the board is new, then the method returns an
        // empty HashSet.
        for (Board boardInMap : this.numsTriedAtBoard.keySet()) {
            if (this.isSameBoard(boardInMap, board)) {
                return this.numsTriedAtBoard.get(boardInMap);
            }
        }
        return new HashSet<>();
    }
    public boolean allNumsTriedOnBoard(Board board) {
        // This method checks if all numbers have been tried on a given board.
        // If all numbers have been tried, then the method returns true.
        // Otherwise, the method returns false.
        for (Board boardInMap : this.numsTriedAtBoard.keySet()) {
            if (this.isSameBoard(boardInMap, board)) {
                if (this.numsTriedAtBoard.get(boardInMap).size() == 9) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean numHasBeenTriedOnBoard(Board board, int num) {
        // This method checks if a number has been tried on a given board. If
        // the number has been tried, then the method returns true. Otherwise,
        // the method returns false.
        for (Board boardInMap : this.numsTriedAtBoard.keySet()) {
            if (this.isSameBoard(boardInMap, board)) {
                if (this.numsTriedAtBoard.get(boardInMap).contains(num)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void addNumToNumsTriedAtBoard(Board board, int num) {
        // This method adds a number to the numsTriedAtBoard HashMap. If the
        // board is new, then a new key is added to the HashMap. If the board
        // is not new, then the number is added to the HashSet at the key of
        // the board.
        boolean boardIsNew = true;
        for (Board boardInMap : this.numsTriedAtBoard.keySet()) {
            if (this.isSameBoard(boardInMap, board)) {
                if (this.numsTriedAtBoard.get(boardInMap) == null) {
                    this.numsTriedAtBoard.put(boardInMap, new HashSet<>(List.of(num)));
                    boardIsNew = false;
                    break;
                }
                this.numsTriedAtBoard.get(boardInMap).add(num);
                boardIsNew = false;
                break;
            }
        }
        if (boardIsNew) {
            this.numsTriedAtBoard.put(board, new HashSet<>(List.of(num)));
        }
    }
    public boolean isSameBoard(Board mapBoard, Board testBoard) {
        return mapBoard.equals(testBoard);
    }
    public int getNextNumber(int num) {
        // If the number is 9, then we need to return 1. Otherwise, we can
        // return the number incremented to get the next number in the
        // sequence.
        if (num == 9) {
            return 1;
        } else {
            return num + 1;
        }
    }
    public int getNextNumber(HashSet<Integer> numsTried) {
        System.out.println(numsTried);
        Random random = new Random();
        int randNum = random.nextInt(9) + 1;
        while (numsTried.contains(randNum)) {
            randNum = random.nextInt(9) + 1;
        }
        return randNum;
    }
    public boolean allNumbersContainsAny(int rowIndex, int columnIndex) {
        // Checking all numbers with the any method. If a number is not
        // contained, then the allNumbersContainAny array will contain a false
        // at the index of the number. If all numbers are contained, then the
        // allNumbersContainAny array will contain all true values and the
        // method will proceed to return true.
        boolean[] allNumbersContainAny = new boolean[9];
        for (int i = 1; i <= 9; i++) {
            if (this.containsAny(rowIndex, columnIndex, i)) {
                allNumbersContainAny[i - 1] = true;
            } else {
                allNumbersContainAny[i - 1] = false;
            }
        }
        for (boolean numIsContained : allNumbersContainAny) {
            if (!numIsContained) {
                return false;
            }
        }
        return true;
    }
    public boolean allNumbersContainsAny(int rowIndex, int columnIndex, HashSet<Integer> numsTried) {
        // Checking all numbers with the any method. If a number is not
        // contained, then the allNumbersContainAny array will contain a false
        // at the index of the number. If all numbers are contained, then the
        // allNumbersContainAny array will contain all true values and the
        // method will proceed to return true.
        if (numsTried == null) {
            numsTried = new HashSet<>();
        }
        boolean[] allNumbersContainAny = new boolean[9];
        for (int i = 1; i <= 9; i++) {
            if (numsTried.contains(i)) {
                allNumbersContainAny[i - 1] = true;
            } else if (this.containsAny(rowIndex, columnIndex, i)) {
                allNumbersContainAny[i - 1] = true;
            } else {
                allNumbersContainAny[i - 1] = false;
            }
        }
        for (boolean numIsContained : allNumbersContainAny) {
            if (!numIsContained) {
                return false;
            }
        }
        return true;
    }

    public boolean containsAny(int rowIndex, int columnIndex, int value) {
        // If another number occurs in the row, column, or square, then the
        // number is contained.
        return this.rowContains(rowIndex, value) ||
                this.columnContains(columnIndex, value) ||
                this.squareContains(this.sudoku.getBoard().fetchSquare(rowIndex + 1, columnIndex + 1), value);
    }
    public boolean rowContains(int rowIndex, int value) {
        // Returns true if the row contains the value.
        int[] rowSet = this.sudoku.getBoard().fetchSetFromRow(rowIndex + 1);
        for (int i = 0; i < 9; i++) {
            if (rowSet[i] == value) {
                return true;
            }
        }
        return false;
    }
    public boolean columnContains(int columnIndex, int value) {
        // Returns true if the column contains the value.
        int[] columnSet = this.sudoku.getBoard().fetchSetFromColumn(columnIndex + 1);
        for (int i = 0; i < 9; i++) {
            if (columnSet[i] == value) {
                return true;
            }
        }
        return false;
    }
    public boolean squareContains(int square, int value) {
        // Returns true if the square contains the value.
        int[] squareSet = this.sudoku.getBoard().fetchSetFromSquare(square);
        for (int i = 0; i < 9; i++) {
            if (squareSet[i] == value) {
                return true;
            }
        }
        return false;
    }
    public HashSet<BoardIndex> getPlaceableIndexes(Board board) {
        HashSet<BoardIndex> placeableIndexes = new HashSet<>();
        for (int rowIndex = 0; rowIndex < this.rowLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.colLength; columnIndex++) {
                if (board.getNumber(rowIndex + 1, columnIndex + 1) == 0) {
                    placeableIndexes.add(new BoardIndex(rowIndex, columnIndex));
                }
            }
        }
        return placeableIndexes;
    }
    public void generateValidMutedBoard(int numMuted) {
        // This method generates a valid muted board by generating a valid
        // board and then muting a given number of indices.
        for (int i = 0; i < numMuted;) {
            BoardIndex randIndex = this.getRandomIndex();
            int randNumber = this.getRandomNumber();
            if (!this.containsAny(randIndex.getRowIndex(), randIndex.getColumnIndex(), randNumber)) {
                this.sudoku.getBoard().placeNumber(randIndex.getRowIndex() + 1, randIndex.getColumnIndex() + 1, randNumber);
                i++;
            }
        }
    }
    public int getRandomNumber() {
        // This method returns a random number between 1 and 9.
        Random random = new Random();
        return random.nextInt(9) + 1;
    }
    public BoardIndex getRandomIndex() {
        // This method returns a random index between 0 and 8.
        int randomRow = this.getRandomNumber() - 1;
        int randomColumn = this.getRandomNumber() - 1;
        return new BoardIndex(randomRow, randomColumn);
    }
    public void seedBoard() {
        this.sudoku.getBoard().placeNumber(1, 1, 4);
    }
    public static void main(String[] args) {
//        int[][] mutedBoardArray = {
//                {4, 0, 0, 0, 0, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {5, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0}
//        };
//        int[][] emptyArray = {
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0}
//        };
//        SudokuGeneratorV2 startingBoardGenerator = new SudokuGeneratorV2(new MutedBoard(emptyArray));
//        startingBoardGenerator.generateValidMutedBoard(4);
//        startingBoardGenerator.sudoku.getBoard().printBoard();
//        System.out.println();
//        SudokuGeneratorV2 sudokuGenerator = new SudokuGeneratorV2(new MutedBoard(startingBoardGenerator.sudoku.getBoard()));
//        sudokuGenerator.generateBoard();
        int[][] boardArray = {
                {4, 2, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        SudokuGeneratorV2 sudokuGenerator = new SudokuGeneratorV2();
        sudokuGenerator.sudoku.getBoard().setBoard(boardArray);
        sudokuGenerator.sudoku.getBoard().printBoard();
        sudokuGenerator.generateBoardRandomly();
        // sudokuGenerator.generateBoard();
        sudokuGenerator.sudoku.getBoard().printBoard();
    }
    public Sudoku getSudoku() {
        return this.sudoku;
    }
}
