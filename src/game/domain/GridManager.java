package game.domain;
import javax.management.openmbean.InvalidKeyException;

public class GridManager {

    private final int[][] grid;
    private int turn = 1;

    private final int xValue = 1;

    private final int oValue = 2;

    public boolean isXTurn() {
        return turn == xValue;
    }

    public int getEmptyValue() {
        return 0;
    }

    public int getXValue() {
        return xValue;
    }

    public int getOValue() {
        return oValue;
    }

    public GridManager(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public int getGridSize() {
        return this.grid.length;
    }

    public boolean isSlotFree(int row, int col) {
        return this.grid[row][col] == this.getEmptyValue();
    }

    public boolean isSlotValid(int row, int col) {
        return (
                row >= 0 &&
                row < this.grid.length &&
                col >= 0 &&
                col < this.grid.length
        );
    }

    public boolean hasFreeSlots() {
        for (int[] row : this.grid) {
            for (int value : row) {
                if (value == this.getEmptyValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGameOver() {
        return (
            this.isXWin() ||
            this.isOWin() ||
            this.isDraw()
        );
    }

    private boolean isWin(int playerVal) {

        // check rows
        for (int[] row: this.grid) {
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
        for (int col = 0; col < this.grid[0].length; col++) {
            isColWin=true;
            for (int[] row : this.grid) {
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
        for (int i = 0; i < this.grid.length; i++) {
            if (this.grid[i][i] != playerVal) {
                isDiagonalLeftTopWin = false;
                break;
            }
        }

        if (isDiagonalLeftTopWin) {
            return true;
        }

        //check diagonal (-1, 0) -> (0, -1)
        boolean isDiagonalRightTopWin = true;
        for (int row = this.grid.length - 1; row >= 0; row--) {
            int col = this.grid.length - 1 - row;
            if (this.grid[row][col] != playerVal) {
                isDiagonalRightTopWin = false;
                break;
            }
        }

        return isDiagonalRightTopWin;
    }

    public boolean isXWin() {
        return this.isWin(this.xValue);
    }

    public boolean isOWin() {
        return this.isWin(this.oValue);
    }

    public boolean isDraw() {
        return (
            !this.hasFreeSlots() &&
            !this.isXWin() &&
            !this.isOWin()
        );
    }

    public void makeMove(int row, int col) {
        if (!isSlotFree(row, col) || !isSlotValid(row, col)){
            throw new InvalidKeyException("slot is already taken");
        }

        if (this.turn == this.xValue){
            this.setX(row, col);
            this.turn = this.oValue;
        } else {
            this.setO(row, col);
            this.turn = this.xValue;
        }

    }

    private void setX(int x, int y) {
        this.grid[x][y] = this.xValue;
    }

    private void setO(int x, int y) {
        this.grid[x][y] = this.oValue;
    }


}
