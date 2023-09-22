package game.domain.models;

import game.etc.Helpers;

public class PlayerLoginInput {

    public String username;
    public String password;

    public PlayerLoginInput(String username, String password) {
        this.username = username;
        this.password = Helpers.hashPassword(password);
    }

}
