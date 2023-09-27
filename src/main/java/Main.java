import kdg.tictactoe.di.TicTacToeProvider;
import kdg.tictactoe.domain.GridFactory;
import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.presentation.ITicTacToe;

public class Main {
    public static void main(String[] args) {

        int[][] grid = GridFactory.newEmptyGrid();

        GridStatefulManager gridStatefulManager = new GridStatefulManager(grid);
        ITicTacToe ticTacToe = TicTacToeProvider.provideTicTacToe(gridStatefulManager);

        ticTacToe.playGame();

    }

}