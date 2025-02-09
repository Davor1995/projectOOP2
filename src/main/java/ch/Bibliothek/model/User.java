package ch.Bibliothek.model;

public class User {
    private String username;
    private String password;

    public boolean authenticate(String username, String password) {

        return this.username.equals(username) && this.password.equals(password);
    }
}
