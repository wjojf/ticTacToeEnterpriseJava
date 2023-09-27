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

        while (!isRandomSlotValid(randomGridSlot)) {
            randomGridSlot = freeSlots.get(rand.nextInt(freeSlots.size()));
        }

        this.gridManager.makeMove(randomGridSlot.row, randomGridSlot.col);
    }

    private void makeBestMove(List<GridSlot> freeSlots) {
        int[][] gridCopy = getCurrentGridCopy();
    }

    private boolean isRandomSlotValid(GridSlot gridSlot) {
        return gridSlot != null && this.gridManager.isSlotFree(gridSlot.row, gridSlot.col);
    }

}
