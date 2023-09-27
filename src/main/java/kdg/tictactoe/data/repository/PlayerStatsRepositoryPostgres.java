package kdg.tictactoe.data.repository;

import kdg.tictactoe.data.manager.DatabaseManager;
import kdg.tictactoe.domain.models.PlayerStats;
import kdg.tictactoe.domain.repository.IPlayerStatsRepository;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStatsRepositoryPostgres implements IPlayerStatsRepository {

    private final DatabaseManager databaseManager;

    public PlayerStatsRepositoryPostgres() throws SQLException {
        this.databaseManager = new DatabaseManager();
    }

    @Override
    public PlayerStats getPlayerStatsByUserID(Integer userId) throws SQLException, InvalidKeyException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM users_games_stats WHERE user_id = ? LIMIT 1;"
        );
        statement.setInt(1, userId);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Integer gamesWon = rs.getInt("games_won");
            Integer gamesLost = rs.getInt("games_lost");
            Integer gamesDraw = rs.getInt("games_draw");

            return new PlayerStats(gamesWon, gamesLost, gamesDraw);
        }

        throw new InvalidKeyException("user stats not found");

    }

    @Override
    public void updatePlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws SQLException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement statement = conn.prepareStatement(
                "UPDATE users_games_stats SET games_won=?, games_draw=?, games_lost=? WHERE user_id = ?"
        );

        statement.setInt(1, playerStats.getGamesWon());
        statement.setInt(2, playerStats.getGamesDraw());
        statement.setInt(3, playerStats.getGamesDraw());
        statement.setInt(4, userId);

        statement.executeUpdate();

        conn.commit();
    }

    @Override
    public void createPlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws SQLException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO users_games_stats (games_won, games_draw, games_lost, user_id) VALUES (?, ?, ?, ?);"
        );

        statement.setInt(1, playerStats.getGamesWon());
        statement.setInt(2, playerStats.getGamesDraw());
        statement.setInt(3, playerStats.getGamesDraw());
        statement.setInt(4, userId);

        statement.executeUpdate();

        conn.commit();
    }
}
