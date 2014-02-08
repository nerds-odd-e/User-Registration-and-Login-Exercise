import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestLoginServlet {

    LoginServlet sut = new LoginServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void username_is_empty() throws ServletException, IOException {
        setRequestParameter("username", "");
        setRequestParameter("password", "password");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?errorCode=EmptyUsername");
    }

    @Test
    public void password_is_empty() throws ServletException, IOException {
        setRequestParameter("username", "username");
        setRequestParameter("password", "");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?errorCode=EmptyPassword");
    }

    private void setRequestParameter(String key, String value) {
        when(request.getParameter(key)).thenReturn(value);
    }

}
