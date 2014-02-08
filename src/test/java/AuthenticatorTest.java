import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticatorTest {

    @Test
    public void successful_authenticate() {
        UserRepository repository = mock(UserRepository.class);
        Authenticator authenticator = new Authenticator(repository);
        User user = new User("username", "password");

        when(repository.find("username")).thenReturn(user);

        boolean actual = authenticator.authenticate("username", "password");

        assertTrue(actual);
    }
}
