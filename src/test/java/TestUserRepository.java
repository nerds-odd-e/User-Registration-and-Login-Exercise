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

    @Before
    public void initialUsersCollection() throws Exception {
        MongoClient client = new MongoClient("192.168.56.103");
        DB db = client.getDB("test");
        users = db.getCollection("Users");
    }

    @Test
    public void find_existing_user_by_name() throws UnknownHostException {
        createUser("anotherUsername", "anotherPassword");
        createUser("username", "password");

        User actualUser = repository.find("username");

        assertEquals("password", actualUser.getPassword());
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
