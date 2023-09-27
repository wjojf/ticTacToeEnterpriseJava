package kdg.tictactoe.domain.manager;

import kdg.tictactoe.domain.models.GridSlot;

import java.util.ArrayList;
import java.util.List;

public class GridStaticManager {

    public static final int xValue = 1;
    public static final int oValue = 2;
    public static final int emptyValue = 0;

    public static boolean isGameOver(int[][] grid) {
        return (
            isXWin(grid) ||
            isOWin(grid) ||
            isDraw(grid)
        );
    }

    public static boolean isXWin(int[][] grid) {
        return isWin(xValue, grid);
    }

    public static boolean isOWin(int[][] grid) {
        return isWin(oValue, grid);
    }

    public static boolean isDraw(int[][] grid) {
        return (
                !isGridHasFreeSlots(grid) &&
                !isXWin(grid) &&
                !isOWin(grid)
        );
    }

    public static boolean isGridHasFreeSlots(int[][] grid) {
        for (int[] row : grid) {
            for (int value : row) {
                if (value == emptyValue) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<GridSlot> getAllEmptySlots(int[][] grid) {
        List<GridSlot> emptySlots = new ArrayList<>();

        for (int row = 0; row < getGridSize(grid); row++) {
            for (int col = 0; row < getGridSize(grid); col++) {
                if (isSlotFree(row, col, grid)) {
                    emptySlots.add(new GridSlot(row, col));
                }
            }
        }

        return emptySlots;
    }

    public static int getGridSize(int[][] grid) {
        return grid.length;
    }

    public static boolean isSlotFree(int row, int col, int[][] grid) {
        return grid[row][col] == emptyValue;
    }

    public static boolean isSlotValid(int row, int col, int[][] grid) {
        return (
                row >= 0 &&
                row < getGridSize(grid) &&
                col >= 0 &&
                col < getGridSize(grid)
        );
    }

    private static boolean isWin(int playerVal, int[][] grid) {

        // check rows
        for (int[] row: grid) {
            boolean isRowWin = true;

            for (int value : row) {
                if (value != playerVal) {
                    isRowWin = false;
                    break;
                }
            }
            if (isRowWin) {
                return true;
            }
        }

        //check columns
        boolean isColWin;
        for (int col = 0; col < getGridSize(grid); col++) {
            isColWin=true;
            for (int[] row : grid) {
                if (row[col] != playerVal) {
                    isColWin = false;
                    break;
                }
            }
            if (isColWin) {
                return true;
            }
        }

        //check diagonal (0, 0) -> (-1, -1)
        boolean isDiagonalLeftTopWin = true;
        for (int i = 0; i < getGridSize(grid); i++) {
            if (grid[i][i] != playerVal) {
                isDiagonalLeftTopWin = false;
                break;
            }
        }

        if (isDiagonalLeftTopWin) {
            return true;
        }

        //check diagonal (-1, 0) -> (0, -1)
        boolean isDiagonalRightTopWin = true;
        for (int row = getGridSize(grid) - 1; row >= 0; row--) {
            int col = getGridSize(grid) - 1 - row;
            if (grid[row][col] != playerVal) {
                isDiagonalRightTopWin = false;
                break;
            }
        }

        return isDiagonalRightTopWin;
    }
}
