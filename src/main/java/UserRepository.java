import com.mongodb.*;

import java.net.UnknownHostException;

public class UserRepository {
    public User find(String username) {
        DBObject user = getUsers().findOne(new BasicDBObject("username", username));

        return new User(username, (String) user.get("password"));
    }

    private DBCollection getUsers() {
        MongoClient client = null;
        try {
            client = new MongoClient("192.168.56.103");
        } catch (UnknownHostException e) {
        }
        return client.getDB("test").getCollection("Users");
    }
}
