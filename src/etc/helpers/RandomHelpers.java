package etc.helpers;

public class RandomHelpers {

    public static int getRandomInteger(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static String getRandomEmail(String domain) {
        String emailAddress = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        while (emailAddress.length() < 5) {
            int character = (int) (Math.random() * 26);
            emailAddress += alphabet.substring(character, character + 1);
            emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
            emailAddress += "@" + domain;
        }

        return emailAddress;
    }
}
