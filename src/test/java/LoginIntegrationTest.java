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

    @Ignore
    @Test
    public void successful_login() throws ServletException, IOException {
        LoginServlet servlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("username");
        when(request.getParameter("password")).thenReturn("password");

        servlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp?result=SuccessfulLogin");
    }
}
