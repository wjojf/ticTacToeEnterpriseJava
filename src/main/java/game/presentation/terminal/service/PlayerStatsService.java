package game.presentation.terminal.service;

import game.di.RepositoryProvider;
import game.domain.models.Player;
import game.domain.models.PlayerStats;
import game.domain.repository.IPlayerStatsRepository;
import game.etc.Helpers;

import java.security.InvalidKeyException;

public class PlayerStatsService
{
    private IPlayerStatsRepository repository;

    public PlayerStatsService() {
        try {
            this.repository = RepositoryProvider.providePlayersStatsRepository();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printCurrentPlayerStats(Player player) {
        PlayerStats currentPlayerStats;

        try {
            currentPlayerStats = this.repository.getPlayerStatsByUserID(player.id);
        }
        catch (Exception e) {
            System.out.printf("Error loading Player stats: %s%n", e.getMessage());
            return;
        }

        System.out.printf(getIntroMessage(player));
        printStatisticsTable(currentPlayerStats);
    }

    public void savePlayerWin(Player player) {
        System.out.println("Saving Player Win Result");

        PlayerStats currentPlayerStats = null;
        try {
            currentPlayerStats = this.repository.getPlayerStatsByUserID(player.id);
        }
        catch (InvalidKeyException e) {
            this.createNewPlayerStats(player);
            savePlayerWin(player);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        assert currentPlayerStats != null;
        currentPlayerStats.updateGamesWon();

        try {
            this.repository.updatePlayerStatsByUserId(player.id, currentPlayerStats);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            savePlayerWin(player);
        }

    }

    public void savePlayerLost(Player player) {
        System.out.println("Saving Player Lost Result");

        PlayerStats currentPlayerStats = null;
        try {
            currentPlayerStats = this.repository.getPlayerStatsByUserID(player.id);
        }
        catch (InvalidKeyException e) {
            this.createNewPlayerStats(player);
            savePlayerLost(player);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        assert currentPlayerStats != null;
        currentPlayerStats.updateGamesLost();

        try {
            this.repository.updatePlayerStatsByUserId(player.id, currentPlayerStats);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            savePlayerLost(player);
        }
    }

    public void savePlayerDraw(Player player) {

        System.out.println("Saving Player Draw Result");

        PlayerStats currentPlayerStats = null;
        try {
            currentPlayerStats = this.repository.getPlayerStatsByUserID(player.id);
        }
        catch (InvalidKeyException e) {
            this.createNewPlayerStats(player);
            savePlayerDraw(player);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        assert currentPlayerStats != null;
        currentPlayerStats.updateGamesDraw();

        try {
            this.repository.updatePlayerStatsByUserId(player.id, currentPlayerStats);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            savePlayerDraw(player);
        }
    }

    private static PlayerStats getDefaultPlayerStats() {
        return new PlayerStats(0, 0, 0);
    }

    private void createNewPlayerStats(Player player) {
        try {
            this.repository.createPlayerStatsByUserId(player.id, getDefaultPlayerStats());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getIntroMessage(Player player) {
        return "Current Statistics for Player %s%n: ".formatted(player.username);
    }

    private static void printStatisticsTable(PlayerStats playerStats) {
        String wonGamesRow =    "| Games Won     | %s".formatted(playerStats.getGamesWon());
        String lostGamesRow =   "| Games Lost    | %s".formatted(playerStats.getGamesLost());
        String drawGamesRow =   "| Games Draw    | %s".formatted(playerStats.getGamesDraw());
        String winPercentRow =  "| Games Won, %  | %s".formatted(playerStats.getWinPercentage());
        String lostPercentRow = "| Games Lost, % | %s".formatted(playerStats.getLossPercentage());
        String drawPercentRow = "| Games Draw, % | %s".formatted(playerStats.getDrawPercentage());

        Integer[] rowLengths = {
                wonGamesRow.length(),
                lostGamesRow.length(),
                drawGamesRow.length(),
                winPercentRow.length(),
                lostPercentRow.length(),
                drawPercentRow.length(),
        };

        Helpers.selectionSort(rowLengths);
        Integer maxLength = rowLengths[5];

        String delimStringRow = "-".repeat(maxLength);

        String[] rowsToDisplay = {
                wonGamesRow,
                lostGamesRow,
                drawGamesRow,
                winPercentRow,
                lostPercentRow,
                drawPercentRow
        };

        for (int i = 0; i < rowsToDisplay.length; i++) {
            rowsToDisplay[i] = rowsToDisplay[i] + " ".repeat(maxLength - rowsToDisplay[i].length()) + "|";
        }

        for (String rowToDisplay : rowsToDisplay) {
            System.out.println(delimStringRow);
            System.out.println(rowToDisplay);
        }
    }
}
