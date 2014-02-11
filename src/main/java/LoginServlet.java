import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    public static final String LOGIN_URL = "index.jsp";

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

        if (getUser(username).authenticate(password))
            resp.sendRedirect(createLoginUrlWithErrorCode("SuccessfulLogin"));
        else
            resp.sendRedirect(createLoginUrlWithErrorCode("FailedLogin"));
    }

    protected User getUser(String username) {
        return User.create(username);
    }

    private String createLoginUrlWithErrorCode(String errorCode) {
        return LOGIN_URL + "?result=" + errorCode;
    }
}
