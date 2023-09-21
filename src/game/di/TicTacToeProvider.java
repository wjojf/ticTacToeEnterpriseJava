package game.di;

import game.domain.GridManager;
import game.presentation.ITicTacToe;
import game.presentation.terminal.TicTacToeFactory;

public class TicTacToeProvider {

    public static ITicTacToe provideTicTacToe(GridManager gridManager) {
        return TicTacToeFactory.newAnonymousTicTacToe(gridManager);
    }


}
