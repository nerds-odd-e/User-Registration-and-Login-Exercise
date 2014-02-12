import com.mongodb.*;

import java.net.UnknownHostException;

public class UserRepository {
    public User find(String username) {
        DBObject user = getUsers().findOne(new BasicDBObject("username", username));

        if (user == null)
            return new NotExistingUser();

        return new User(username, (String) user.get("password"));
    }

    private DBCollection getUsers() {
        MongoClient client = null;
        try {
            client = new MongoClient(System.getProperty("mongo.host"));
        } catch (UnknownHostException e) {
        }
        return client.getDB(System.getProperty("mongo.dbname")).getCollection("Users");
    }
}
