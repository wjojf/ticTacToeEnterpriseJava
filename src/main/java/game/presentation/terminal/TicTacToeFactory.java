package game.presentation.terminal;

import game.domain.GridManager;

public class TicTacToeFactory {
    public static TicTacToeAnonymus newAnonymousTicTacToe(GridManager gridManager) {
        return new TicTacToeAnonymus(gridManager);
    }
}
