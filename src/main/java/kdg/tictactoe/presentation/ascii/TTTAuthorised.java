package kdg.tictactoe.presentation.ascii;

import kdg.tictactoe.presentation.ascii.service.PlayerStatsService;
import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.domain.models.Player;
import kdg.tictactoe.presentation.ascii.service.AuthorizationService;


public class TTTAuthorised extends TTTAnonymus {

    private Player player;

    private final AuthorizationService authorizationService;

    private final PlayerStatsService playerStatsService;

    public TTTAuthorised(GridStatefulManager gridStatefulManager) {
        super(gridStatefulManager);
        this.authorizationService = new AuthorizationService();
        this.playerStatsService = new PlayerStatsService();
    }

    @Override
    public void playGame() {
        this.authorizationService.authorizePlayer();
        this.player = this.authorizationService.getPlayer();

        System.out.println("Successfully Authorized Player");

        this.playerStatsService.printCurrentPlayerStats(player);

        super.playGame();
    }

    @Override
    protected void handleGameOver() {

        boolean isDraw = this.gridStatefulManager.isDraw();

        if (isDraw) {
            System.out.println("The Game ended as a draw.");
            this.playerStatsService.savePlayerDraw(player);
            return;
        }

        boolean isXWin = this.gridStatefulManager.isXWin();
        if (isXWin) {
            System.out.println("X player won! Congratulations");
            this.playerStatsService.savePlayerWin(player);
            return;
        }

        boolean isOWin = this.gridStatefulManager.isOWin();
        if (isOWin) {
            System.out.println("You lost! Come back to test your skills again!");
            this.playerStatsService.savePlayerLost(player);
        }
    }

}
