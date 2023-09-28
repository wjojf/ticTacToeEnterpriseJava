package kdg.tictactoe.presentation.ascii;

import kdg.tictactoe.domain.manager.GridStatefulManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TicTacToeFactory {
    @Contract("_ -> new")
    public static @NotNull TTTAnonymus newAnonymousTicTacToe(GridStatefulManager gridStatefulManager) {
        return new TTTAnonymus(gridStatefulManager);
    }

    @Contract("_ -> new")
    public static @NotNull TTTAuthorised newAuthorizedTicTacToe(GridStatefulManager gridStatefulManager) {
        return new TTTAuthorised(gridStatefulManager);
    }

    @Contract("_ -> new")
    public static @NotNull TTTAuthorizedAI newAuthorizedAITicTacToe(GridStatefulManager gridStatefulManager) {
        return new TTTAuthorizedAI(gridStatefulManager);
    }

}
