package etc.models;

public class User {

    public User(int ID, String email) {
        this.setEmail(email);
        this.setID(ID);
    }

    private Integer ID;

    public int getID() {
        return ID;
    }
    private void setID(int val) {
        this.ID = val;
    }

    private String email;

    public String getEmail() {
        return email;
    }
    private void setEmail(String email) {
        this.email = email;
    }

    public String getHelloMessage() {
        return "%s says hello!".formatted(this.email);
    }


    private String name;

    public String getName() {
        return this.name;
    }

    private void setName(String value) {
        this.name = value;
    }
}
