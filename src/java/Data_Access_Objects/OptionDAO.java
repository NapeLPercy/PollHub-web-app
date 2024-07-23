package Data_Access_Objects;

import Database_Manager.PollHubDB;
import Entities.Option;
import Utils.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OptionDAO {

    private final PollHubDB connection;

    public OptionDAO() throws SQLException, ClassNotFoundException {
        connection = new PollHubDB();
    }

    public boolean checkIdExist(int option_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT option_id FROM pollhubdb.option WHERE option_id = ?";
        boolean hasOption = false;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, option_id);
            try (ResultSet rs = ps.executeQuery()) {
                hasOption = rs.next();
                rs.close();
            }
            ps.close();
        }
        
        return hasOption;
    }//end

    public boolean addOptions(ArrayList<Option> pollOptions, int poll_id) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO pollhubdb.option "
                + "VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            for (Option option : pollOptions) {
                
                //get user_id
                Random random = new Random();
                int option_id = random.randomizeTableID();
                
                while (checkIdExist(option_id)) {
                    option_id = random.randomizeTableID();
                }
                
                String optionText = option.getOptionText();
                int voteCount = option.getVoteCount();
                
                ps.setInt(1, option_id);
                ps.setString(2, optionText);
                ps.setInt(3, voteCount);
                ps.setInt(4, poll_id);
                
                ps.addBatch();
            }
            
            ps.executeBatch();
            ps.close();
        }
        return true;
    }//end

    //increase the number of votes for an whenever it gets clicked on
    public boolean increaseOptionVoteCount(int optionId, int currentVoteCount) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE pollhubdb.option "
                + "SET VoteCount = ? "
                + "WHERE Option_Id = ?";
        currentVoteCount++;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, currentVoteCount);
            ps.setInt(2, optionId);
            ps.executeUpdate();
            ps.close();
        }

        return true;
    }//end

    //get the number of votes for a particular poll option
    public int getOptionVoteCount(int optionId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT VoteCount FROM pollhubdb.option "
                + "WHERE Option_Id = ?";
        int voteCount = 0;

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, optionId);

            try (ResultSet rs = ps.executeQuery()) {
                voteCount = rs.next() ? rs.getInt("VoteCount") : 0;
                rs.close();
            }
            ps.close();
        }

        
        return voteCount;
    }//end

    public ArrayList<Option> getRelatedPollOptions(int pollId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT Option_Id, VoteCount FROM pollhubdb.option "
                + "WHERE Poll_Id = ?";

        ArrayList<Option> options = new ArrayList<>();

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, pollId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Option option = new Option();

                    option.setId(rs.getInt("Option_Id"));
                    option.setVoteCount(rs.getInt("VoteCount"));
                    options.add(option);
                }
                rs.close();
            }

            ps.close();
        }

        return options;
    }//end

}
