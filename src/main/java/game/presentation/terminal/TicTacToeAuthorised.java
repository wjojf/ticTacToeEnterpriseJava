package game.presentation.terminal;

import game.domain.GridManager;
import game.domain.repository.IPlayerStatsRepository;
import game.domain.repository.IPlayersRepository;

public class TicTacToeAuthorised extends TicTacToeAnonymus {

    private IPlayersRepository playersRepository;
    private IPlayerStatsRepository playerStatsRepository;

    public TicTacToeAuthorised(GridManager gridManager) {
        super(gridManager);

    }
}
