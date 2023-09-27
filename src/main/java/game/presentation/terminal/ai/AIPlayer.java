package game.presentation.terminal.ai;

import game.domain.GridManager;
import game.domain.models.GridSlot;

import java.util.List;
import java.util.Random;

public class AIPlayer {

    private final GridManager gridManager;
    private final AIDifficulty difficulty;

    public AIPlayer(GridManager gridManager, AIDifficulty difficulty) {
        this.gridManager = gridManager;
        this.difficulty = difficulty;
    }

    public void makeMove() {
        List<GridSlot> freeSlots = this.gridManager.getAllEmptySlots();

        //TODO: finish me

    }

    private int[][] getCurrentGridCopy() {
        return this.gridManager.getGrid().clone();
    }

    private void makeRandomMove(List<GridSlot> freeSlots) {
        Random rand = new Random();
        GridSlot randomGridSlot = null;

        while (randomGridSlot == null || !this.gridManager.isSlotFree(randomGridSlot.row, randomGridSlot.col)) {
            randomGridSlot = freeSlots.get(rand.nextInt(freeSlots.size()));
        }

        this.gridManager.makeMove(randomGridSlot.row, randomGridSlot.col);
    }

    private void makeBestMove(List<GridSlot> freeSlots) {
        int[][] gridCopy = getCurrentGridCopy();
    }

}
