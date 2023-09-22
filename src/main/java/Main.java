import game.di.TicTacToeProvider;
import game.domain.GridFactory;
import game.domain.GridManager;
import game.presentation.ITicTacToe;

public class Main {
    public static void main(String[] args) {

        int[][] grid = GridFactory.newEmptyGrid();

        GridManager gridManager = new GridManager(grid);
        ITicTacToe ticTacToe = TicTacToeProvider.provideTicTacToe(gridManager);

        ticTacToe.playGame();

    }

}