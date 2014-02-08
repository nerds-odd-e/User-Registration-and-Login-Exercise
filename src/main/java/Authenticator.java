public class Authenticator {

    private final UserRepository repository;

    public Authenticator() {
        this(new UserRepository());
    }

    public Authenticator(UserRepository repository) {
        this.repository = repository;
    }

    public boolean authenticate(String username, String password) {
        User user = repository.find(username);
        return user.getPassword().equals(password);
    }
}
