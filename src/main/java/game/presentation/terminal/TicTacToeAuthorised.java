package game.presentation.terminal;

import game.di.RepositoryProvider;
import game.domain.GridManager;
import game.domain.models.Player;
import game.domain.models.PlayerLoginInput;
import game.domain.repository.IPlayerStatsRepository;
import game.domain.repository.IPlayersRepository;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToeAuthorised extends TicTacToeAnonymus {

    private IPlayersRepository playersRepository;
    private IPlayerStatsRepository playerStatsRepository;
    private Player player;

    public TicTacToeAuthorised(GridManager gridManager) {
        super(gridManager);

        try {
            this.playersRepository = RepositoryProvider.providePlayersRepository();
        }
        catch (Exception e) {
            return;
        }

        try {
            this.playerStatsRepository = RepositoryProvider.providePlayersStatsRepository();
        }
        catch (Exception e) {
            return;
        }
    }
}
