package com.chargepoint.game;

import java.util.Arrays;

public class ConwayGameOfLife {
    public static void main(String args[]) {
        int numberOfTicks = 3; //modify this field to change the number of ticks
        int noOfRowsInGrid = 4;
        int numberOfColumnsInGrid = 5;

        //Initialise grid, assuming dead cells in grid as 0 and alive cells as 1
        // We define a fixed grid size of rows and columns instead of an infinite one
        int[][] seedGrid = {{0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        //printing seedGrid
        System.out.println("Initial State of Universe");
        for (int i = 0; i < noOfRowsInGrid; i++) {
            for (int j = 0; j < numberOfColumnsInGrid; j++) {
                System.out.print(seedGrid[i][j]);
            }
            System.out.println();
        }

        //printing output after each tick
        for (int tick = 1; tick <= numberOfTicks; tick++) {
            seedGrid = Arrays.stream(computeNextGenerationGridOnTick(seedGrid, noOfRowsInGrid, numberOfColumnsInGrid))
                    .map(int[]::clone).toArray(int[][]::new); //copy the 2D-array
            System.out.println("Next Generation of State of Universe after tick " + tick);
            for (int i = 0; i < noOfRowsInGrid; i++) {
                for (int j = 0; j < numberOfColumnsInGrid; j++) {
                    System.out.print(seedGrid[i][j]);
                }
                System.out.println();
            }
        }

    }

    static int[][] computeNextGenerationGridOnTick(int[][] seedGrid, int noOfRowsInGrid, int numberOfColumnsInGrid) {
        int[][] nextGenGrid = new int[noOfRowsInGrid][numberOfColumnsInGrid];

        for (int row = 0; row < noOfRowsInGrid; row++) {
            for (int column = 0; column < numberOfColumnsInGrid; column++) { //iterate one by one the cells in grid

                int numberOfLiveNeighbourCells = 0;

                //iterate to find 8 neighbours of current cell
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((row + i) >= 0 && (row + i) < noOfRowsInGrid  // check if there are no row overflows from grid
                                && (column + j) >= 0 && (column + j) < numberOfColumnsInGrid //check no column overflows
                            //  && (i != 0 && j != 0)
                        ) //check if not current grid
                            numberOfLiveNeighbourCells = numberOfLiveNeighbourCells + seedGrid[row + i][column + j];
                    }
                }

                numberOfLiveNeighbourCells = numberOfLiveNeighbourCells - seedGrid[row][column];
                
                //case for under population
                if (seedGrid[row][column] == 1 && numberOfLiveNeighbourCells < 2) {
                    nextGenGrid[row][column] = 0;
                }

                //lives to next generation
                else if (seedGrid[row][column] == 1 && (numberOfLiveNeighbourCells == 2 || numberOfLiveNeighbourCells == 3)) {
                    nextGenGrid[row][column] = 1;
                }

                //case of overcrowding
                else if (seedGrid[row][column] == 1 && (numberOfLiveNeighbourCells > 3)) {
                    nextGenGrid[row][column] = 0;
                }

                //case of reproduction
                else if (seedGrid[row][column] == 0 && (numberOfLiveNeighbourCells == 3)) {
                    nextGenGrid[row][column] = 1;
                } else {
                    nextGenGrid[row][column] = seedGrid[row][column]; //any other cases
                }

            }
        }

        return nextGenGrid;
    }
}