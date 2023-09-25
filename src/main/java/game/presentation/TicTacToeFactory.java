package game.presentation;

import game.domain.GridManager;
import game.presentation.terminal.TicTacToeAnonymus;
import game.presentation.terminal.TicTacToeAuthorised;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TicTacToeFactory {
    @Contract("_ -> new")
    public static @NotNull TicTacToeAnonymus newAnonymousTicTacToe(GridManager gridManager) {
        return new TicTacToeAnonymus(gridManager);
    }

    @Contract("_ -> new")
    public static @NotNull TicTacToeAuthorised newAuthorizedTicTacToe(GridManager gridManager) {
        return new TicTacToeAuthorised(gridManager);
    }

}
