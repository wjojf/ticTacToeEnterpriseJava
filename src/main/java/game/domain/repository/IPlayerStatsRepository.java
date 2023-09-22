package game.domain.repository;

import game.domain.models.PlayerStats;

public interface IPlayerStatsRepository {
    public PlayerStats getPlayerStatsByUserID(Integer userId);
}
