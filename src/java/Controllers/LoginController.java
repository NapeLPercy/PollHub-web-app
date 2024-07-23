package Controllers;

import Entities.Account;
import Services.AccountService;
import Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class LoginController extends HttpServlet {

    private AccountService accountService;
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            accountService = new AccountService();

            boolean isValid = accountService.validateLogin(new Account(username, password));
            if (isValid) {
                userService = new UserService();
                String userId = userService.getUserId(username, password);

                HttpSession session = request.getSession(true);
                session.setAttribute("userId", userId);

            }

            JSONObject obj = new JSONObject();
            obj.put("valid", isValid);
            PrintWriter out = response.getWriter();
            out.write(obj.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
