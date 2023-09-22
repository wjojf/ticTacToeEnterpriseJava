package game.domain;

public class GridFactory {

    public static int[][] newEmptyGrid() {
        return new int[][]{{0,0,0}, {0,0,0}, {0,0,0}};
    }

    public static int[][] newBottomDiagonalWinGrid() {
        return new int[][]{{0,0,1},{0,1,0}, {1, 0, 0}};
    }

    public static int[][] newDrawGrid() {
        return new int[][]{
            {1, 2, 1},
            {2, 2, 1},
            {1, 1, 2},
        };
    }
}
