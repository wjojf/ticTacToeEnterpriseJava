package etc.models;

import etc.helpers.RandomHelpers;

public class UserFactory {

    public static User newUser(int ID, String email) {
        return new User(ID, email);
    }

    public static User newRandomUser() {
        int id = RandomHelpers.getRandomInteger(1, 100);
        String email = RandomHelpers.getRandomEmail("google.com");

        return new User(id, email);
    }

}
