package kdg.tictactoe.presentation.terminal;

import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.domain.manager.GridStaticManager;
import kdg.tictactoe.domain.models.PlayerMove;
import kdg.tictactoe.presentation.terminal.ai.AIDifficulty;
import kdg.tictactoe.presentation.terminal.ai.AIPlayer;
import kdg.tictactoe.presentation.terminal.models.PlayerMoveInput;


public class TTTAuthorizedAI extends TTTAuthorised {

    private final AIPlayer aiPlayer;
    private final int aiPlayerGridValue;
    private final int realPlayerGridValue;

    public TTTAuthorizedAI(GridStatefulManager gridStatefulManager) {
        super(gridStatefulManager);
        aiPlayerGridValue = GridStaticManager.oValue;
        realPlayerGridValue=GridStaticManager.xValue;
        aiPlayer = new AIPlayer(gridStatefulManager, AIDifficulty.HARD, realPlayerGridValue, aiPlayerGridValue);
    }


    @Override
    public PlayerMoveInput takePlayerMoveInput() {
        if (!isAIMove()) {
            return super.takePlayerMoveInput();
        }

        PlayerMove aiMoveDomain = aiPlayer.getAIMove();
        PlayerMoveInput aiMoveInput =  PlayerMoveInput.FromPlayerMoveDomain(aiMoveDomain, rowIndexes);

        System.out.printf("AI Move: %s.%s%n", aiMoveInput.row, aiMoveInput.col);

        return aiMoveInput;
    }

    private boolean isAIMove() {
        return (
                gridStatefulManager.isXTurn() && aiPlayerGridValue == GridStaticManager.xValue ||
                        aiPlayerGridValue == GridStaticManager.oValue
        );
    }

}
