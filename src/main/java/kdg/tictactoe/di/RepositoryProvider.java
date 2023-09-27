package kdg.tictactoe.di;

import kdg.tictactoe.domain.repository.IPlayerStatsRepository;
import kdg.tictactoe.data.repository.PlayerStatsRepositoryPostgres;
import kdg.tictactoe.data.repository.PlayersRepositoryPostgres;
import kdg.tictactoe.domain.repository.IPlayersRepository;

public class RepositoryProvider {
    public static IPlayersRepository providePlayersRepository() throws Exception {
        return new PlayersRepositoryPostgres();
    }

    public static IPlayerStatsRepository providePlayersStatsRepository() throws Exception {
        return new PlayerStatsRepositoryPostgres();
    }

}
