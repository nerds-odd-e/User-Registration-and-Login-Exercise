import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUserAuthenticate {

    UserRepository repository = mock(UserRepository.class);
    private User user;

    @Before
    public void createUser() {
        when(repository.find("username")).thenReturn(new User("username", "password"));
    }

    @Test
    public void successful_user_authenticate() {
        user = User.create("username", repository);

        assertTrue(user.authenticate("password"));
    }

    @Test
    public void failed_user_authenticate() {
        user = User.create("username", repository);

        assertFalse(user.authenticate("wrongPassword"));
    }
}
