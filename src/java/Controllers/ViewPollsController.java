package Controllers;

import Entities.Poll;
import Services.PollService;
import Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewPollsController extends HttpServlet {
    
    private PollService pollService;
    private UserService userService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            pollService = new PollService();
            userService = new UserService();
            
            ArrayList<Poll> polls = pollService.getAllPolls();
            for (int i = 0; i < polls.size(); i++) {
                
                String creator = userService.getUser(Integer.parseInt(polls.get(i).getUser_id()));
                polls.get(i).setUser_id(creator);
            }
            request.setAttribute("polls", polls);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ViewPollsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/Views/Outputs/ViewPolls.jsp").forward(request, response);
    }
}
