package Controllers;

import Entities.Account;
import Entities.User;
import Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateAccountController extends HttpServlet {

    private UserService userService;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");      
        String password = request.getParameter("password");
        
        Account account = new Account(username, password);
        User user = new User(name, surname, email, account);
        
        
        try {
            
            userService = new UserService();
            boolean userAdded = userService.addUser(user);
            System.out.println(userAdded);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
