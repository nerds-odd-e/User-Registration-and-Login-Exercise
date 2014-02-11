public class User {
    private final String password;
    private final String username;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public static User create(String username, UserRepository repository) {
        return repository.find(username);
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public static User create(String username) {
        return create(username, new UserRepository());
    }
}
