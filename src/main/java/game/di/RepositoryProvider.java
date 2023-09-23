package game.di;

import game.data.repository.PlayerStatsRepositoryPostgres;
import game.data.repository.PlayersRepositoryPostgres;
import game.domain.repository.IPlayerStatsRepository;
import game.domain.repository.IPlayersRepository;

public class RepositoryProvider {
    public static IPlayersRepository providePlayersRepository() throws Exception {
        return new PlayersRepositoryPostgres();
    }

    public static IPlayerStatsRepository providePlayersStatsRepository() throws Exception {
        return new PlayerStatsRepositoryPostgres();
    }

}
