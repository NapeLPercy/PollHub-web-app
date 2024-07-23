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
            
            String userId = request.getSession().getAttribute("userId").toString();
            ArrayList<Poll> userPolls = voteService.getPollService().getAllPolls();//get user polls

            JSONObject data = new JSONObject();
            
            for (Poll poll : userPolls) {
                ArrayList<Option> options = poll.getPollOptions();
                
                for (int i = 0; i < options.size(); i++) {
                    Option option = options.get(i);
                   // System.out.println("Total poll votes: "+pollVoteCount+" ==== option votes "+option.getVoteCount());
                    double perc = ((double) optionVoteCount / (double) pollVoteCount) * 100;
                   // System.out.println(perc);
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
