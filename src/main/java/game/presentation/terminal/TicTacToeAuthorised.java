package game.presentation.terminal;

import game.di.RepositoryProvider;
import game.domain.GridManager;
import game.domain.models.Player;
import game.domain.repository.IPlayerStatsRepository;
import game.domain.repository.IPlayersRepository;

import java.util.Objects;
import java.util.Scanner;

public class TicTacToeAuthorised extends TicTacToeAnonymus {

    private IPlayersRepository playersRepository;
    private IPlayerStatsRepository playerStatsRepository;
    private Player player;

    private final String loginInputValueLower = "l";
    private final String registerInputValueLower = "r";

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

    public void authorizePlayer() {
        String loginRegisterInput = this.takeLoginRegisterInput();

        // TODO: finish me
        if (Objects.equals(loginRegisterInput, this.loginInputValueLower)) {
            this.loginPlayer();
            return;
        }

        this.registerPlayer();

    }

    private String takeLoginRegisterInput() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please Log In or Sign Up");

        String loginRegister = null;

        while (!isLoginRegisterInputValid(loginRegister)){
            System.out.print(this.getLoginInputMessage());
            loginRegister = keyboard.nextLine().toLowerCase();
        }

        return loginRegister;

    }

    private void loginPlayer() {

    }

    private void registerPlayer() {

    }

    private String getLoginInputMessage() {
        return "Enter %s/%s to Log In or %s/%s to Sign Up: ".formatted(
                this.loginInputValueLower,
                this.loginInputValueLower.toUpperCase(),
                this.registerInputValueLower,
                this.registerInputValueLower.toUpperCase()
        );
    }

    private boolean isLoginRegisterInputValid(String loginRegisterInput) {
        loginRegisterInput = loginRegisterInput.toLowerCase();
        return (
                Objects.equals(loginRegisterInput, "l") ||
                Objects.equals(loginRegisterInput, "r")
        );
    }

}
