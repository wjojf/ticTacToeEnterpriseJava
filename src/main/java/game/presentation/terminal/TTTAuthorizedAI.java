package game.presentation.terminal;

import game.domain.GridManager;
import game.presentation.terminal.ai.AIDifficulty;
import game.presentation.terminal.ai.AIPlayer;


public class TTTAuthorizedAI extends TicTacToeAuthorised {

    private final AIPlayer aiPlayer;

    public TTTAuthorizedAI(GridManager gridManager) {
        super(gridManager);
        aiPlayer = new AIPlayer(gridManager, AIDifficulty.EASY);
    }
}
