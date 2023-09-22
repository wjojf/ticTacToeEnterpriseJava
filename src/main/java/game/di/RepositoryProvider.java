package game.di;

import game.data.repository.PlayersRepositoryPostgres;
import game.domain.repository.IPlayersRepository;

public class RepositoryProvider {
    public static IPlayersRepository providePlayersRepository() throws Exception {
        return new PlayersRepositoryPostgres();
    }
}
