import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestLoginServlet {

    Authenticator authenticator = mock(Authenticator.class);
    LoginServlet sut = new LoginServlet(authenticator);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void username_is_empty() throws ServletException, IOException {
        setRequestParameter("username", "");
        setRequestParameter("password", "password");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=EmptyUsername");
    }

    @Test
    public void password_is_empty() throws ServletException, IOException {
        setRequestParameter("username", "username");
        setRequestParameter("password", "");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=EmptyPassword");
    }

    @Test
    public void successful_login() throws ServletException, IOException {
        setRequestParameter("username", "username");
        setRequestParameter("password", "password");
        when(authenticator.authenticate("username", "password")).thenReturn(true);

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=SuccessfulLogin");
    }

    @Test
    public void failed_login() throws ServletException, IOException {
        setRequestParameter("username", "username");
        setRequestParameter("password", "wrongPassword");
        when(authenticator.authenticate("username", "wrongPassword")).thenReturn(false);

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=FailedLogin");
    }

    private void setRequestParameter(String key, String value) {
        when(request.getParameter(key)).thenReturn(value);
    }

}
