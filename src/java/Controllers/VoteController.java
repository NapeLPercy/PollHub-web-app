package Controllers;

import Entities.Option;
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

public class VoteController extends HttpServlet {

    private VoteService voteService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //CHECK IF THE USER_ID AND THE POLL_ID EXISTS ON THE SAME ROW IN THE DATABASE
            //IF THEY DID VOTE, REMOVE THIER USER_ID, VOTED OPTION AND POLLiD FROM THE VOTE TABLE
            //ADD THIER NEW VOTED OPTION IN THE DATABASE
            //REDUCE THE VOTE COUNT FOR A PREVIOUS VOTED OPTION

            voteService = new VoteService();

            String id = request.getParameter("id");

            int pollId = voteService.getVotedPollId(id);
            int optionId = voteService.getVotedOptionId(id);

            int beforeVoteCount = voteService.getOptionService().getOptionVoteCount(optionId);
            
            //USER VOTE FOR A CERTAIN POLL OPTION
            boolean didVote = voteService.getOptionService().increaseOptionVoteCount(optionId, beforeVoteCount);
            
            //ADD THE USER_ID AND POLL_ID AND THE CHOSEN OPTION TO THE VOTE TABLE
            //USE OPTION ID TO GET THE OPTION
            double totalPollVotes = voteService.getPollService().getTotalPollVoteCount(pollId);
            
            ArrayList<Option> relatedPollOption = voteService.getOptionService().getRelatedPollOptions(pollId);
            if (didVote) {

                int afterVoteCount = voteService.getOptionService().getOptionVoteCount(optionId);

                JSONObject data = new JSONObject();

                data.put("Vote_count", afterVoteCount);
                data.put("Meter_id", id);
                data.put("Total_votes", totalPollVotes);

              for(Option option : relatedPollOption){
                  
                  double perc = (option.getVoteCount()/totalPollVotes)*100;
                  perc = Math.ceil(perc*100.00)/100.00;
                  data.put(option.getId()+"-option", perc);
              }

                PrintWriter writer = response.getWriter();
                writer.write(data.toString());
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
