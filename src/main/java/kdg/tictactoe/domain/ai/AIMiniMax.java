package kdg.tictactoe.domain.ai;

import kdg.tictactoe.domain.manager.GridStaticManager;
import kdg.tictactoe.domain.models.GridSlot;
import kdg.tictactoe.domain.models.PlayerMove;
import java.util.List;

public class AIMiniMax {

    private final int[][] gridCopy;
    private final int aiPlayerGridValue;
    private final int realPlayerGridValue;

    public AIMiniMax(int[][] grid, int realPlayerGridValue, int aiPlayerGridValue) {
        this.gridCopy = grid;
        this.aiPlayerGridValue = aiPlayerGridValue;
        this.realPlayerGridValue = realPlayerGridValue;
    }

    public PlayerMove getBestMove() {

        int bestScore = -2;
        PlayerMove bestMove = null;

        List<GridSlot> freeSlots = GridStaticManager.getAllEmptySlots(gridCopy);

        for (GridSlot freeSlot: freeSlots) {

            //Simulate AI move
            gridCopy[freeSlot.row][freeSlot.col] = aiPlayerGridValue;

            // Get minimax best score with maximizing = false as its Player move now
            int score = miniMax(gridCopy, 0, false);

            if (score > bestScore) {
                bestScore = score;
                bestMove = new PlayerMove(freeSlot.row, freeSlot.col);

            }

            gridCopy[freeSlot.row][freeSlot.col] = GridStaticManager.emptyValue;
        }

        return bestMove;
    }

    private int miniMax(int[][] grid, int depth, boolean isMaximizing) {

        if (GridStaticManager.isGameOver(grid)) {
            return getGameOverScore(grid);
        }

        List<GridSlot> freeSlots = GridStaticManager.getAllEmptySlots(grid);

        // Search for best possible output - maximizing
        if (isMaximizing) {
            int bestScore = -1;

            for (GridSlot freeSlot : freeSlots) {
                grid[freeSlot.row][freeSlot.col] = aiPlayerGridValue;
                int score = miniMax(grid, depth + 1, false);
                grid[freeSlot.row][freeSlot.col] = GridStaticManager.emptyValue;
                bestScore = Math.max(bestScore, score);
            }

            return bestScore;
        }

        // Search for worst possible output - minimizing

        int worstScore = 1;

        for (GridSlot freeSlot: freeSlots) {
            grid[freeSlot.row][freeSlot.col] = realPlayerGridValue;

            // Get worst for AI Score with Maximizing = true as next move is AI
            int score = miniMax(grid, depth + 1, true);

            grid[freeSlot.row][freeSlot.col] = GridStaticManager.emptyValue;

            worstScore = Math.min(worstScore, score);

        }

        return worstScore;
    }

    private int getGameOverScore(int[][] grid) {
        // Draw - 0
        if (GridStaticManager.isDraw(grid)) {
            return 0;
        }

        // Win - +1
        if (
            (GridStaticManager.isXWin(grid) && aiPlayerGridValue == GridStaticManager.xValue) ||
            (GridStaticManager.isOWin(grid) && aiPlayerGridValue == GridStaticManager.oValue)
        ) {
            return 1;
        }

        // Loss - -1
        return -1;
    }
}
