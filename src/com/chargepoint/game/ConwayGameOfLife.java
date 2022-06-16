package com.chargepoint.game;

/**
 * The class provides the necessary logic to compute next generation grid based on an input 2D-grid array.
 * The 2-D grid input array is assumed to have rows and columns with either value '0' or '1'. A '0' denote a dead
 * cell in the Conway Game of Life and '1' denotes an alive cell in the game.
 */
public class ConwayGameOfLife {

    /**
     * This method compute the next generation grid based on a given seed grid input. The seed grid is assumed
     * to have cell with value as either '0' or '1'. The output of this method is a new 2D-array. This 2D-array has
     * it's cell values computed based on the game rules (which include finding total alive neighbour cells
     * for a given cell and certain other criteria)
     *
     * @param seedGrid              - the 2D-int array for which next generation grid need to be computed
     * @param noOfRowsInGrid        - the total number of rows for the 2D-array
     * @param numberOfColumnsInGrid - the total number of columns for the 2D-array
     * @return - the computed next generation grid/2D-array based on game rules
     */
    int[][] computeNextGenerationGridOnTick(int[][] seedGrid, int noOfRowsInGrid, int numberOfColumnsInGrid) {
        int[][] nextGenGrid = new int[noOfRowsInGrid][numberOfColumnsInGrid];

        for (int row = 0; row < noOfRowsInGrid; row++) {
            for (int column = 0; column < numberOfColumnsInGrid; column++) { //iterate one by one the cells in grid

                int numberOfLiveNeighbourCells = computeNumberOfLiveNeighbourCells(seedGrid, row, column,
                        noOfRowsInGrid, numberOfColumnsInGrid);

                //case for under population or overcrowding
                if (seedGrid[row][column] == 1 && (numberOfLiveNeighbourCells < 2 || numberOfLiveNeighbourCells > 3)) {
                    nextGenGrid[row][column] = 0;
                }
                //case of reproduction
                else if (seedGrid[row][column] == 0 && (numberOfLiveNeighbourCells == 3)) {
                    nextGenGrid[row][column] = 1;
                }
                //any other cases including case of next generation
                else {
                    nextGenGrid[row][column] = seedGrid[row][column];
                }

            }
        }

        return nextGenGrid;
    }

    /**
     * This method computes the total number of alive neighbours cells around a given cell in a 2-D array.
     * The neighbouring cells of a given cell are its eight neighbors, which are the cells
     * that are horizontally, vertically, or diagonally adjacent. The alive cells are the neighbouring
     * cells with value '1'
     *
     * @param seedGrid              - the 2D-int array
     * @param row                   - the row of the cell in the 2D-array
     * @param column                - the column of the cell in the 2D-array
     * @param noOfRowsInGrid        - the total number of rows for the 2D-array
     * @param numberOfColumnsInGrid - the total number of columns for the 2D-array
     * @return - computes the total number of alive neighbouring cells for a given cell
     */
    int computeNumberOfLiveNeighbourCells(int[][] seedGrid, int row, int column,
                                          int noOfRowsInGrid, int numberOfColumnsInGrid) {
        int numberOfLiveNeighbourCells = 0;

        //iterate to find 8 neighbours of current cell
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((row + i) >= 0 && (row + i) < noOfRowsInGrid  // check if there are no row overflows from grid
                        && (column + j) >= 0 && (column + j) < numberOfColumnsInGrid) //check no column overflows
                {
                    numberOfLiveNeighbourCells = numberOfLiveNeighbourCells + seedGrid[row + i][column + j];
                }
            }
        }
        //remove the seedGrid[row][column] from the computation
        numberOfLiveNeighbourCells = numberOfLiveNeighbourCells - seedGrid[row][column];

        return numberOfLiveNeighbourCells;
    }
}