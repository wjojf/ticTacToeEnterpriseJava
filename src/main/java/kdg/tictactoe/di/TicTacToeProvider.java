package kdg.tictactoe.di;

import kdg.tictactoe.presentation.ITicTacToe;
import kdg.tictactoe.presentation.ascii.TicTacToeFactory;
import kdg.tictactoe.domain.manager.GridStatefulManager;

public class TicTacToeProvider {

    public static ITicTacToe provideTicTacToe(GridStatefulManager gridStatefulManager) {
        return TicTacToeFactory.newAuthorizedAITicTacToe(gridStatefulManager);
    }

}
