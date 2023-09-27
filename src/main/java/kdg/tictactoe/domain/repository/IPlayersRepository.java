package kdg.tictactoe.domain.repository;

import kdg.tictactoe.domain.models.Player;
import kdg.tictactoe.domain.models.PlayerLoginInput;

public interface IPlayersRepository {
    boolean isUserExists(PlayerLoginInput loginInput) throws Exception;
    boolean isPasswordMatched(PlayerLoginInput loginInput) throws Exception;
    void createNewUser(PlayerLoginInput loginInput) throws Exception;
    Player getPlayerByUsername(String username) throws Exception;
}
