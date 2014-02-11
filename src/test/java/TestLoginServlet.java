import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class TestLoginServlet {

    class TestClass4LoginServlet extends LoginServlet {

        private User user = mock(User.class);

        protected User getUser(String username) {
            return user;
        }

    }

    TestClass4LoginServlet sut = new TestClass4LoginServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void username_is_empty() throws ServletException, IOException {
        setRequestUsernameAndPasswordParameters("", "password");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=EmptyUsername");
    }

    @Test
    public void password_is_empty() throws ServletException, IOException {
        setRequestUsernameAndPasswordParameters("username", "");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=EmptyPassword");
    }

    @Test
    public void successful_login() throws ServletException, IOException {
        setRequestUsernameAndPasswordParameters("username", "password");
        when(sut.user.authenticate("password")).thenReturn(true);

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=SuccessfulLogin");
    }

    @Test
    public void failed_login() throws ServletException, IOException {
        setRequestUsernameAndPasswordParameters("username", "wrongPassword");
        when(sut.user.authenticate("wrongPassword")).thenReturn(false);

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=FailedLogin");
    }

    private void setRequestParameter(String key, String value) {
        when(request.getParameter(key)).thenReturn(value);
    }

    private void setRequestUsernameAndPasswordParameters(String username, String password) {
        setRequestParameter("username", username);
        setRequestParameter("password", password);
    }

}
