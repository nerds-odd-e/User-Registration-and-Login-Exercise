import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginIntegrationTest {

    private DBCollection users;

    @Before
    public void initialUsersCollection() throws Exception {
        MongoClient client = new MongoClient("192.168.56.103");
        DB db = client.getDB("test");
        users = db.getCollection("Users");
    }

    @Test
    public void successful_login() throws ServletException, IOException {
        createUser();
        LoginServlet servlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("password");

        servlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=SuccessfulLogin");
    }

    @After
    public void dropUsers() {
        users.drop();
    }

    private void createUser() {
        BasicDBObject user = new BasicDBObject("username", "username").
                append("password", "password");
        users.insert(user);
    }
}
