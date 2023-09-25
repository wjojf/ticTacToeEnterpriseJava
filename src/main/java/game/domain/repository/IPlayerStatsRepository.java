package game.domain.repository;

import game.domain.models.PlayerStats;

public interface IPlayerStatsRepository {
    PlayerStats getPlayerStatsByUserID(Integer userId) throws Exception;
    void updatePlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws Exception;
    void createPlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws Exception;

}
