package kdg.tictactoe.presentation.terminal.ai;

import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.domain.models.GridSlot;
import kdg.tictactoe.domain.models.PlayerMove;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AIMiniMax {

    private final GridStatefulManager gridStatefulManager;
    private final int aiPlayerGridValue;

    public AIMiniMax(@NotNull GridStatefulManager gridStatefulManager) {
        this.gridStatefulManager = gridStatefulManager;
        this.aiPlayerGridValue = gridStatefulManager.getOValue();
    }


    public PlayerMove getBestMove() {
        int bestScore = -2;
        PlayerMove bestMove = null;
        List<GridSlot> freeSlots = this.gridStatefulManager.getAllEmptySlots();

        int[][] gridCopy = getCurrentGridCopy();

        for (GridSlot freeSlot: freeSlots) {
            gridCopy[freeSlot.row][freeSlot.col] = aiPlayerGridValue;

            int score = miniMax(gridCopy, 0, true);

            if (score > bestScore) {
                bestScore = score;
                bestMove = new PlayerMove(freeSlot.row, freeSlot.col);

            }

            gridCopy[freeSlot.row][freeSlot.col] = gridStatefulManager.getEmptyValue();
        }

        return bestMove;
    }

    private int miniMax(int[][] board, int depth, boolean isMaximizing) {

        int drawScore = 0;
        int aiWinScore = 1;
        int aiLoseScore = -1;

        if (isMaximizing) {

        }

        return aiWinScore;
    }

    private int[][] getCurrentGridCopy() {
        return this.gridStatefulManager.getGrid().clone();
    }


}
