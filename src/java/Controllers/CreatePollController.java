package Controllers;

import Entities.Option;
import Entities.Poll;
import Entities.Vote;
import Services.PollService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreatePollController extends HttpServlet {

    private PollService pollService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String question = request.getParameter("question");
        String topic = request.getParameter("topic");
        String[] options = request.getParameterValues("option");

        ArrayList<Vote> pollVotes = new ArrayList<>();

        try {

            pollService = new PollService();

            //poll options
            ArrayList<Option> pollOptions = pollService.getPollOptions(options);
            Poll poll = new Poll(question, "Active", topic, new Date(), pollVotes, pollOptions);

            int user_id = 1673;
            boolean isAdded = pollService.addPoll(poll, user_id);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreatePollController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("Views/Outputs/PollCreatedOutcome.jsp").forward(request, response);
    }

}
