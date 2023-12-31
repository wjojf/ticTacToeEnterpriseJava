package kdg.tictactoe.presentation.ascii.models;

import kdg.tictactoe.domain.models.PlayerMove;
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

    public static @NotNull PlayerMoveInput FromPlayerMoveDomain(PlayerMove playerMove, char[] rowIndexes) {
        return new PlayerMoveInput(
                String.valueOf(rowIndexes[playerMove.row]),
                Integer.valueOf(playerMove.col + 1).toString()
        );
    }

}
