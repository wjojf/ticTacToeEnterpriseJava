package kdg.tictactoe.domain.manager;

import javax.management.openmbean.InvalidKeyException;

public class GridStatefulManager {
    private final int[][] grid;
    private int turn = GridStaticManager.xValue;
    private final int xValue = GridStaticManager.xValue;
    private final int oValue = GridStaticManager.oValue;
    public GridStatefulManager(int[][] grid) {
        this.grid = grid;
    }

    public void makeMove(int row, int col) throws InvalidKeyException {
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

    public boolean isXTurn() {
        return turn == xValue;
    }

    public int getXValue() {
        return xValue;
    }

    public int getOValue() {
        return oValue;
    }

    public int[][] getGrid() {return grid;}

    public int getGridSize() {
        return GridStaticManager.getGridSize(grid);
    }

    public boolean isSlotFree(int row, int col) {
        return GridStaticManager.isSlotFree(row, col, grid);
    }

    public boolean isSlotValid(int row, int col) {
        return GridStaticManager.isSlotValid(row, col, grid);
    }

    public boolean isGameOver() {
        return GridStaticManager.isGameOver(grid);
    }

    public boolean isXWin() {
        return GridStaticManager.isXWin(grid);
    }

    public boolean isOWin() {
        return GridStaticManager.isOWin(grid);
    }

    public boolean isDraw() {
        return GridStaticManager.isDraw(grid);
    }

    private void setX(int x, int y) {
        this.grid[x][y] = this.xValue;
    }

    private void setO(int x, int y) {
        this.grid[x][y] = this.oValue;
    }

}
