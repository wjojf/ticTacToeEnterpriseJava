package game.domain.models;

public class PlayerStats {
    private Integer gamesWon = 0;

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void updateGamesWon() {
        this.gamesWon++;
    }

    private Integer gamesLost = 0;

    public void updateGamesLost() {
        this.gamesLost++;
    }
    public Integer getGamesLost() {
        return gamesLost;
    }

    private Integer gamesDraw = 0;

    public void updateGamesDraw() {
        this.gamesDraw++;
    }

    public Integer getGamesDraw() {
        return gamesDraw;
    }


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

    public Double getDrawPercentage() {
        Integer totalGames = this.getTotalGames();

        if (totalGames == 0) {
            return 0.0;
        }

        return this.gamesDraw.doubleValue() / totalGames.doubleValue();
    }
}
