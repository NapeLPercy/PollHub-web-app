package Controllers;

import Entities.Option;
import Entities.Poll;
import Services.VoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class RenderUserPollsController extends HttpServlet {

    private VoteService voteService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        //System.out.println(id);
        try {

            voteService = new VoteService();
            int optionId = voteService.getVotedOptionId(id);
            int pollId = voteService.getVotedPollId(id);

            int optionVoteCount = voteService.getOptionService().getOptionVoteCount(optionId);
            double pollVoteCount = voteService.getPollService().getTotalPollVoteCount(pollId);

            ArrayList<Poll> userPolls = voteService.getPollService().getAllPolls();//get user polls

            JSONObject data = new JSONObject();

            for (Poll poll : userPolls) {
                ArrayList<Option> options = poll.getPollOptions();

                for (Option option : options) {
                    double perc = ((double) optionVoteCount / (double) pollVoteCount) * 100;
                    perc = Math.ceil(perc * 100.00) / 100.00;
                    data.put(id, perc);
                }
            }

            PrintWriter writer = response.getWriter();
            writer.write(data.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RenderUserPollsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
