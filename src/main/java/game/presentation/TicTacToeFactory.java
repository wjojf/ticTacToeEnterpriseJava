package game.presentation;

import game.domain.GridManager;
import game.presentation.terminal.TicTacToeAnonymus;

public class TicTacToeFactory {
    public static TicTacToeAnonymus newAnonymousTicTacToe(GridManager gridManager) {
        return new TicTacToeAnonymus(gridManager);
    }
}
