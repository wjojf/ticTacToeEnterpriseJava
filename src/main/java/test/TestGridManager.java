package test;

import game.domain.GridFactory;
import game.domain.GridManager;

public class TestGridManager {

    public static void TestGridManagerIsWin() {
        int[][] grid = GridFactory.newBottomDiagonalWinGrid();
        GridManager gridManger = new GridManager(grid);

        boolean isXWin = gridManger.isXWin();
        if (!isXWin) {
            System.out.println("TEST DIAGONAL (-1, 0) -> (0, -1) FAILED");
        } else {
            System.out.println("TEST DIAGONAL (-1, 0) -> (0, -1) PASSED!");
        }

    }

    public static void TestGridManagerIsDraw() {
        int[][] grid = GridFactory.newDrawGrid();
        GridManager gridManger = new GridManager(grid);

        boolean isDraw = gridManger.isDraw();
        if (!isDraw) {
            System.out.println("TEST isDraw FAILED");
        } else {
            System.out.println("TEST isDraw PASSED!");
        }
    }

}
