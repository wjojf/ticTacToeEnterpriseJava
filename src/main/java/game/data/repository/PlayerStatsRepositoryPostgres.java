package game.data.repository;

import game.data.manager.DatabaseManager;
import game.domain.models.PlayerStats;
import game.domain.repository.IPlayerStatsRepository;

import java.security.InvalidKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStatsRepositoryPostgres implements IPlayerStatsRepository {

    private final DatabaseManager databaseManager;

    public PlayerStatsRepositoryPostgres(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public PlayerStats getPlayerStatsByUserID(Integer userId) throws SQLException, InvalidKeyException {
        Connection conn = this.databaseManager.getConnection();

        PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM users_games_stats WHERE user_id = ? LIMIT 1;"
        );
        statement.setInt(1, userId);

        ResultSet rs = statement.executeQuery();

        if (!rs.first()) {
            throw new InvalidKeyException("user stats not found");
        }

        Integer gamesWon = rs.getInt("games_won");
        Integer gamesLost = rs.getInt("games_lost");
        Integer gamesDraw = rs.getInt("games_draw");

        return new PlayerStats(gamesWon, gamesLost, gamesDraw);

    }

    @Override
    public void updatePlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws Exception {

    }

    @Override
    public void createPlayerStatusByUserId(Integer userId, PlayerStats playerStats) throws Exception {

    }
}
