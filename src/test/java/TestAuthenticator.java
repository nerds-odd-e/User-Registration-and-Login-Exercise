import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAuthenticator {

    UserRepository repository = mock(UserRepository.class);
    Authenticator authenticator = new Authenticator(repository);

    @Before
    public void createUser() {
        when(repository.find("username")).thenReturn(new User("username", "password"));
    }

    @Test
    public void successful_authenticate() {
        assertTrue(authenticator.authenticate("username", "password"));
    }

    @Test
    public void failed_authenticate() {
        assertFalse(authenticator.authenticate("username", "wrongPassword"));
    }

}
