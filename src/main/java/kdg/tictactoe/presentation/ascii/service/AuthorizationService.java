package kdg.tictactoe.presentation.ascii.service;

import kdg.tictactoe.di.RepositoryProvider;
import kdg.tictactoe.domain.models.Player;
import kdg.tictactoe.domain.models.PlayerLoginInput;
import kdg.tictactoe.domain.repository.IPlayersRepository;

import java.util.Objects;
import java.util.Scanner;

public class AuthorizationService
{

    private final String loginInputValueLower = "l";
    private final String registerInputValueLower = "r";
    private IPlayersRepository playersRepository;

    private Player player = null;

    public Player getPlayer() {
        return player;
    }

    public AuthorizationService() {
        try {
            this.playersRepository = RepositoryProvider.providePlayersRepository();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void authorizePlayer() {
        while (!isAuthenticated())
        {
            String loginRegisterInput = takeLoginRegisterInput();

            PlayerLoginInput loginRegisterCredentials = takePlayerLoginCredentials();

            handleLoginInput(loginRegisterInput, loginRegisterCredentials);
        }
    }

    private void loginPlayer(PlayerLoginInput loginInput) throws Exception {
        boolean playerExists = this.playersRepository.isUserExists(loginInput);

        if (!playerExists) {
            throw new IllegalArgumentException("user not found");
        }

        boolean isPasswordOk = this.playersRepository.isPasswordMatched(loginInput);
        if (!isPasswordOk) {
            throw new IllegalArgumentException("invalid password");
        }

        this.player = this.playersRepository.getPlayerByUsername(loginInput.username);
    }

    private void registerPlayer(PlayerLoginInput registerCredentials) throws Exception {
        this.playersRepository.createNewUser(registerCredentials);
        this.player = this.playersRepository.getPlayerByUsername(registerCredentials.username);
    }

    private String takeLoginRegisterInput() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nPlease Log In or Sign Up");

        String loginRegister = null;

        while (!isLoginRegisterInputValid(loginRegister)){
            System.out.print("\n" + this.getLoginInputMessage());
            loginRegister = keyboard.nextLine().toLowerCase();
        }

        return loginRegister;
    }

    private PlayerLoginInput takePlayerLoginCredentials() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = keyboard.nextLine();

        System.out.println("-".repeat(10));

        System.out.print("Enter password: ");
        String password = keyboard.nextLine();

        System.out.println("-".repeat(10));

        return new PlayerLoginInput(username, password);
    }

    private void handleLoginInput(String loginRegisterInput, PlayerLoginInput loginRegisterCredentials) {
        if (Objects.equals(loginRegisterInput, loginInputValueLower)) {
            try {
                loginPlayer(loginRegisterCredentials);
            }
            catch (Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
            return;
        }

        try {
            registerPlayer(loginRegisterCredentials);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

        if (loginRegisterInput == null) {
            return false;
        }

        return (
                loginRegisterInput.toLowerCase().equals(this.loginInputValueLower) ||
                loginRegisterInput.toLowerCase().equals(this.registerInputValueLower)
        );
    }

    private boolean isAuthenticated() {
        return this.player != null;
    }

}
