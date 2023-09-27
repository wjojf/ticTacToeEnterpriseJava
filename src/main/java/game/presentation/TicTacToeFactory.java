package game.presentation;

import game.domain.GridManager;
import game.presentation.terminal.TTTAnonymus;
import game.presentation.terminal.TTTAuthorised;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TicTacToeFactory {
    @Contract("_ -> new")
    public static @NotNull TTTAnonymus newAnonymousTicTacToe(GridManager gridManager) {
        return new TTTAnonymus(gridManager);
    }

    @Contract("_ -> new")
    public static @NotNull TTTAuthorised newAuthorizedTicTacToe(GridManager gridManager) {
        return new TTTAuthorised(gridManager);
    }

}
