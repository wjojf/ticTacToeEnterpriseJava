package game.domain.models;

import java.text.DecimalFormat;

import static java.lang.Math.round;

public class PlayerStats {
    private Integer gamesWon = 0;
    private Integer gamesLost = 0;
    private Integer gamesDraw = 0;

    public PlayerStats(Integer gamesWon, Integer gamesLost, Integer gamesDraw) {
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesDraw = gamesDraw;
    }

    public Integer getTotalGames() {
        return this.gamesDraw + this.gamesLost + this.gamesWon;
    }

    public Double getWinPercentage() {
        Integer totalGames = this.getTotalGames();

        if (totalGames == 0) {
            return 0.0;
        }

        return this.gamesWon.doubleValue() / totalGames.doubleValue();
    }

    public Double getLossPercentage() {
        Integer totalGames = this.getTotalGames();

        if (totalGames == 0) {
            return 0.0;
        }

        return this.gamesLost.doubleValue() / totalGames.doubleValue();
    }
}
