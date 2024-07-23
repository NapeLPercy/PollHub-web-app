package Services;

import Data_Access_Objects.OptionDAO;
import Entities.Option;
import java.sql.SQLException;
import java.util.ArrayList;

public class OptionService {

    private final OptionDAO optionDao;

    public OptionService() throws SQLException, ClassNotFoundException {
        optionDao = new OptionDAO();
    }

    boolean addOptions(ArrayList<Option> pollOptions, int poll_id) throws SQLException, ClassNotFoundException {
        boolean isAdded = optionDao.addOptions(pollOptions, poll_id);
        return isAdded;
    }//end

    public int getOptionVoteCount(int optionId) throws SQLException, ClassNotFoundException {
        return optionDao.getOptionVoteCount(optionId);
    }//end

    public boolean increaseOptionVoteCount(int optionId, int currentVoteCount) throws SQLException, ClassNotFoundException {
        return optionDao.increaseOptionVoteCount(optionId, currentVoteCount);
    }//end

    public ArrayList<Option> getRelatedPollOptions(int pollId) throws SQLException, ClassNotFoundException {
        return optionDao.getRelatedPollOptions(pollId);
    }//end

}
