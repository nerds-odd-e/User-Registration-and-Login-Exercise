import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestLoginServlet {

    @Test
    public void username_is_empty() throws ServletException, IOException {
        LoginServlet sut = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("");

        sut.doPost(request, response);

        verify(response).sendRedirect("index.jsp?errorCode=EmptyUsername");
    }

}
