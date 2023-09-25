package game.domain.repository;

import game.domain.models.PlayerStats;

import java.security.InvalidKeyException;

public interface IPlayerStatsRepository {
    public PlayerStats getPlayerStatsByUserID(Integer userId) throws Exception, InvalidKeyException;
    public void updatePlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws Exception;
    public void createPlayerStatsByUserId(Integer userId, PlayerStats playerStats) throws Exception;

}
