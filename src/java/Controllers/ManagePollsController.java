package Controllers;

import Entities.Poll;
import Services.PollService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagePollsController extends HttpServlet {

    private PollService pollService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            pollService = new PollService();

            String userId = (String) request.getSession().getAttribute("userId");
            ArrayList<Poll> polls = pollService.getMyPolls(userId);

            request.setAttribute("polls", polls);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManagePollsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/ProcessPolls.jsp").forward(request, response);
    }
}
