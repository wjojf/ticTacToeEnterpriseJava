package game.presentation.terminal;

import game.di.RepositoryProvider;
import game.domain.models.Player;
import game.domain.models.PlayerLoginInput;
import game.domain.repository.IPlayerStatsRepository;
import game.domain.repository.IPlayersRepository;

import java.util.Objects;
import java.util.Scanner;

public class AuthorizationService
{

    private final String loginInputValueLower = "l";
    private final String registerInputValueLower = "r";

    private IPlayersRepository playersRepository;
    private Player player;

    public AuthorizationService() {
        try {
            this.playersRepository = RepositoryProvider.providePlayersRepository();
        }
        catch (Exception e) {
            return;
        }
    }

    public void authorizePlayer() {

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

    private Player loginPlayer(PlayerLoginInput loginInput) throws Exception {
        boolean playerExists = this.playersRepository.isUserExists(loginInput);

        if (!playerExists) {
            throw new IllegalArgumentException("user not found");
        }

        boolean isPasswordOk = this.playersRepository.isPasswordMatched(loginInput);
        if (!isPasswordOk) {
            throw new IllegalArgumentException("invalid password");
        }

        return this.playersRepository.getPlayerByUsername(loginInput.username);
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
        return (
            loginRegisterInput.toLowerCase() == this.loginInputValueLower ||
            loginRegisterInput.toLowerCase() == this.registerInputValueLower
        );
    }

}
