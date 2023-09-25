package game.presentation.terminal.models;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class PlayerMoveInput {
    public String row;
    public String col;

    public PlayerMoveInput(String row, String col) {
        this.row = row;
        this.col = col;
    }

    public static @NotNull PlayerMoveInput FromStringInput(String playerInput) {
        String[] playerInputSplit = playerInput.split(Pattern.quote("."));

        if (playerInputSplit.length != 2) {
            throw new IllegalArgumentException("invalid cell format");
        }

        return new PlayerMoveInput(playerInputSplit[0], playerInputSplit[1]);
    }

}
