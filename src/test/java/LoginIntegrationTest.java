import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Before;
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

    LoginServlet servlet = new LoginServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void createUser() throws Exception {
        MongoClient client = new MongoClient(MongoDB.Host);
        DB db = client.getDB(MongoDB.PROPERTY);
        users = db.getCollection("Users");
        BasicDBObject user = new BasicDBObject("username", "username").
                append("password", "password");
        users.insert(user);
    }

    @Test
    public void successful_login() throws ServletException, IOException {
        setRequestUsernameAndPasswordParameters("username", "password");

        servlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=SuccessfulLogin");
    }

    @Test
    public void failed_login() throws ServletException, IOException {
        setRequestUsernameAndPasswordParameters("username", "wrongPassword");

        servlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=FailedLogin");
    }

    private void setRequestUsernameAndPasswordParameters(String username, String password) {
        setRequestParameter("username", username);
        setRequestParameter("password", password);
    }

    private void setRequestParameter(String key, String value) {
        when(request.getParameter(key)).thenReturn(value);
    }

    @After
    public void dropUsers() {
        users.drop();
    }

}
