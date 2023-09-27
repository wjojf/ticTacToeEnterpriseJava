package kdg.tictactoe.presentation.terminal;

import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.presentation.terminal.ai.AIDifficulty;
import kdg.tictactoe.presentation.terminal.ai.AIPlayer;


public class TTTAuthorizedAI extends TTTAuthorised {

    private final AIPlayer aiPlayer;

    public TTTAuthorizedAI(GridStatefulManager gridStatefulManager) {
        super(gridStatefulManager);
        aiPlayer = new AIPlayer(gridStatefulManager, AIDifficulty.EASY);
    }
}
