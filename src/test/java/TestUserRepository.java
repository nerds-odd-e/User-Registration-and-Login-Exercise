import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;


public class TestUserRepository {

    private DBCollection users;
    private final UserRepository repository = new UserRepository();
    private User actualUser;

    @Before
    public void initialUsersCollection() throws Exception {
        MongoClient client = new MongoClient(MongoDB.Host);
        DB db = client.getDB(MongoDB.PROPERTY);
        users = db.getCollection("Users");
    }

    @Test
    public void find_existing_user_by_name() throws UnknownHostException {
        createUser("anotherUsername", "anotherPassword");
        createUser("username", "password");

        actualUser = repository.find("username");

        assertEquals("password", actualUser.getPassword());
    }

    @Test
    public void cannot_find_user_by_name() {
        createUser("username", "password");

        actualUser = repository.find("notExistingUsername");

        assertEquals("", actualUser.getUsername());
        assertEquals("", actualUser.getPassword());
    }

    private void createUser(String username, String password) {
        BasicDBObject anotherUser = new BasicDBObject("username", username).
                append("password", password);
        users.insert(anotherUser);
    }

    @After
    public void dropUsers() {
        users.drop();
    }
}
