package game.domain.repository;

import game.domain.models.Player;
import game.domain.models.PlayerLoginInput;

public interface IPlayersRepository {
    public boolean isUserExists(PlayerLoginInput loginInput) throws Exception;
    public boolean isPasswordMatched(PlayerLoginInput loginInput) throws Exception;
    public void createNewUser(PlayerLoginInput loginInput) throws Exception;
    public Player getPlayerByUsername(String username) throws Exception;
}
