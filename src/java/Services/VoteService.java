package Services;

import java.sql.SQLException;

public class VoteService {

    private final OptionService optionService;
    private final PollService pollService;

    public VoteService() throws SQLException, ClassNotFoundException {
        optionService = new OptionService();
        pollService = new PollService();
    }

    public OptionService getOptionService() {
        return optionService;
    }

    public PollService getPollService() {
        return pollService;
    }

    //get the id of the selected option from the current poll from a string
    public int getVotedOptionId(String requestId) {
        return Integer.parseInt(requestId.split("-")[0]);
    }//end

    //get the id of the current poll from a string
    public int getVotedPollId(String requestId) {
        return Integer.parseInt(requestId.split("-")[1]);
    }//end

}
