package kdg.tictactoe.presentation.ascii.ai;

import kdg.tictactoe.domain.ai.AIDifficulty;
import kdg.tictactoe.domain.ai.AIMiniMax;
import kdg.tictactoe.domain.manager.GridStaticManager;
import kdg.tictactoe.domain.models.GridSlot;
import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.domain.models.PlayerMove;

import java.util.List;
import java.util.Random;

public class AIPlayer {

    private final GridStatefulManager gridStatefulManager;
    private final AIDifficulty difficulty;

    private final AIMiniMax aiMiniMax;

    public AIPlayer(
            GridStatefulManager gridStatefulManager,
            AIDifficulty difficulty,
            int aiPlayerGridValue,
            int realPlayerGridValue
        ) {
        this.gridStatefulManager = gridStatefulManager;
        this.difficulty = difficulty;
        this.aiMiniMax = new AIMiniMax(gridStatefulManager.getGrid(), realPlayerGridValue, aiPlayerGridValue);
    }

    public PlayerMove getAIMove() {
        List<GridSlot> freeSlots = GridStaticManager.getAllEmptySlots(gridStatefulManager.getGrid());

        switch (difficulty) {
            case EASY -> {
                System.out.println("\nAI Making random Move");
                return getRandomMove(freeSlots);
            }
            case HARD -> {
                System.out.println("\nAI Finding Optimal Move...\n");
                return getBestMove();
            }
        }

        return getBestMove();
    }

    private PlayerMove getRandomMove(List<GridSlot> freeSlots) {
        Random rand = new Random();
        GridSlot randomGridSlot = null;

        while (!isRandomSlotValid(randomGridSlot)) {
            randomGridSlot = freeSlots.get(rand.nextInt(freeSlots.size()));
        }

        return new PlayerMove(randomGridSlot.row, randomGridSlot.col);
    }

    private PlayerMove getBestMove() {
        return aiMiniMax.getBestMove();
    }

    private boolean isRandomSlotValid(GridSlot gridSlot) {
        return gridSlot != null && this.gridStatefulManager.isSlotFree(gridSlot.row, gridSlot.col);
    }

}
