package Services;

import Data_Access_Objects.PollDAO;
import Entities.Option;
import Entities.Poll;
import java.sql.SQLException;
import java.util.ArrayList;

public class PollService {

    private final PollDAO pollDAO;

    public PollService() throws SQLException, ClassNotFoundException {
        pollDAO = new PollDAO();
    }

    public ArrayList<Option> getPollOptions(String[] pollOptions) {

        ArrayList<Option> options = new ArrayList<>();
        for (String optionText : pollOptions) {
            int voteCount = 0;
            Option option = new Option(optionText, voteCount);
            options.add(option);
        }
        return options;
    }//end

    public boolean addPoll(Poll poll, int user_id) throws SQLException, ClassNotFoundException {
        boolean isAdded = pollDAO.addPoll(poll, user_id);

        OptionService optionService = new OptionService();
        if (isAdded) {
            //add poll options
            optionService.addOptions(poll.getPollOptions(), poll.getPollId());
        }
        return isAdded;
    }//end

    public ArrayList<Poll> getAllPolls() throws SQLException, ClassNotFoundException {
        return pollDAO.getAllPolls();
    }//end

    public int getTotalPollVoteCount(int pollId) throws SQLException, ClassNotFoundException {
        return pollDAO.getTotalPollVoteCount(pollId);
    }//end

    private int getPollId(String id) {

        String pollId = "";
        if (id.substring(0, 6).equals("delete")) {
            pollId = id.substring(7, id.length());

        } else if (id.substring(0, 3).equals("dea")) {
            pollId = id.substring(11, id.length());
        } else {
            pollId = id.substring(9, id.length());
        }

        return Integer.parseInt(pollId);
    }//end

    public boolean deletePoll(String id) throws SQLException, ClassNotFoundException {

        int pollId = getPollId(id);
        boolean isDeleted = pollDAO.deletePoll(pollId);
        return isDeleted;
    }//end

    public boolean updatePollStatus(String id, String status) throws SQLException, ClassNotFoundException {

        int pollId = getPollId(id);
        boolean isUpdated = pollDAO.updatePollStatus(pollId, status);
        return isUpdated;
    }//end

    public ArrayList<Poll> getMyPolls(String id) throws SQLException, ClassNotFoundException {
        return pollDAO.getMyPolls(id);
    }//end

}
