package com.chargepoint.game;

public class ConwayGameOfLife {

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