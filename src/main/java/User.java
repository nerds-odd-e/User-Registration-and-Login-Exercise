public class User {
    private final String password;

    public User(String username, String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
