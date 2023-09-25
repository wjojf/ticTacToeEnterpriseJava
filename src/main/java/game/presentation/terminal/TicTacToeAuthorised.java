package game.presentation.terminal;

import game.domain.GridManager;
import game.domain.models.Player;
import game.presentation.terminal.service.AuthorizationService;
import game.presentation.terminal.service.PlayerStatsService;


public class TicTacToeAuthorised extends TicTacToeAnonymus {

    private Player player;
    private final AuthorizationService authorizationService;

    private final PlayerStatsService playerStatsService;

    public TicTacToeAuthorised(GridManager gridManager) {
        super(gridManager);

        this.authorizationService = new AuthorizationService();

        this.playerStatsService = new PlayerStatsService();

    }

    @Override
    public void playGame() {
        this.authorizationService.authorizePlayer();
        this.player = this.authorizationService.getPlayer();

        this.playerStatsService.printCurrentPlayerStats(player);

        super.playGame();
    }

    @Override
    protected void handleGameOver() {

        boolean isDraw = this.gridManager.isDraw();

        if (isDraw) {
            System.out.println("The Game ended as a draw.");
            this.playerStatsService.savePlayerDraw(player);
            return;
        }

        boolean isXWin = this.gridManager.isXWin();
        if (isXWin) {
            System.out.println("X player won! Congratulations");
            this.playerStatsService.savePlayerWin(player);
            return;
        }

        boolean isOWin = this.gridManager.isOWin();
        if (isOWin) {
            System.out.println("You lost! Come back to test your skills again!");
            this.playerStatsService.savePlayerLost(player);
        }
    }

}
