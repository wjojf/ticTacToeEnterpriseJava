package kdg.tictactoe.presentation.terminal.ai;

import kdg.tictactoe.domain.models.GridSlot;
import kdg.tictactoe.domain.manager.GridStatefulManager;

import java.util.List;
import java.util.Random;

public class AIPlayer {

    private final GridStatefulManager gridStatefulManager;
    private final AIDifficulty difficulty;

    private final AIMiniMax aiMiniMax;

    public AIPlayer(GridStatefulManager gridStatefulManager, AIDifficulty difficulty) {
        this.gridStatefulManager = gridStatefulManager;
        this.difficulty = difficulty;
        this.aiMiniMax = new AIMiniMax(gridStatefulManager.getGrid());
    }

    public void makeMove() {
        List<GridSlot> freeSlots = this.gridStatefulManager.getAllEmptySlots();

        switch (difficulty) {
            case EASY -> {
                makeRandomMove(freeSlots);
            }
            case HARD -> {
                makeBestMove(freeSlots);
            }
        }

    }

    private void makeRandomMove(List<GridSlot> freeSlots) {
        Random rand = new Random();
        GridSlot randomGridSlot = null;

        while (!isRandomSlotValid(randomGridSlot)) {
            randomGridSlot = freeSlots.get(rand.nextInt(freeSlots.size()));
        }

        this.gridStatefulManager.makeMove(randomGridSlot.row, randomGridSlot.col);
    }

    private void makeBestMove(List<GridSlot> freeSlots) {
        //FIXME: implement AI algorithm
        makeRandomMove(freeSlots);
    }

    private boolean isRandomSlotValid(GridSlot gridSlot) {
        return gridSlot != null && this.gridStatefulManager.isSlotFree(gridSlot.row, gridSlot.col);
    }

}
