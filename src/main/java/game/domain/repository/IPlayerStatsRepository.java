package game.domain.repository;

import game.domain.models.PlayerStats;

import java.sql.SQLException;

public interface IPlayerStatsRepository {
    public PlayerStats getPlayerStatsByUserID(Integer userId) throws Exception;
    public void updatePlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws Exception;
    public void createPlayerStatusByUserId(Integer userId, PlayerStats playerStats) throws Exception;

}
