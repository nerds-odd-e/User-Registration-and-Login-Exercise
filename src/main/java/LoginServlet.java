import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public static final String LOGIN_URL = "index.jsp";
    private final Authenticator authenticator;

    public LoginServlet() {
        this(new Authenticator());
    }

    public LoginServlet(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        if (password.equals("")) {
            resp.sendRedirect(createLoginUrlWithErrorCode("EmptyPassword"));
            return;
        }

        String username = req.getParameter("username");
        if (username.equals("")) {
            resp.sendRedirect(createLoginUrlWithErrorCode("EmptyUsername"));
            return;
        }

        if (authenticator.authenticate(username, password))
            resp.sendRedirect(createLoginUrlWithErrorCode("SuccessfulLogin"));
        else
            resp.sendRedirect(createLoginUrlWithErrorCode("FailedLogin"));
    }

    private String createLoginUrlWithErrorCode(String errorCode) {
        return LOGIN_URL + "?result=" + errorCode;
    }
}
