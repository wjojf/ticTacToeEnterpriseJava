import kdg.etc.Helpers;

public class TestHashPassword {

    public static void main(String[] args) {
        String passwordToHash = "12345";
        System.out.println(Helpers.hashPassword(passwordToHash));
    }

}
