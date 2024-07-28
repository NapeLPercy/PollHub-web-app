package Data_Access_Objects;

import Database_Manager.PollHubDB;
import Entities.Option;
import Entities.Poll;
import Utils.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PollDAO {

    private final PollHubDB connection;

    public PollDAO() throws SQLException, ClassNotFoundException {
        connection = new PollHubDB();
    }

    private boolean checkIdExist(int poll_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT poll_id FROM pollhubdb.poll WHERE poll_id = ?";

        boolean hasId = false;

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, poll_id);
            try (ResultSet rs = ps.executeQuery()) {
                hasId = rs.next();
                rs.close();
            }
            ps.close();
        }

        return hasId;
    }//end

    public boolean addPoll(Poll poll, int user_id) throws SQLException, ClassNotFoundException {

        //get user_id
        Random random = new Random();
        int poll_id = random.randomizeTableID();

        while (checkIdExist(poll_id)) {
            user_id = random.randomizeTableID();
        }

        poll.setPollId(poll_id);//set poll id so it can be a fk

        String sql = "INSERT INTO pollhubdb.poll "
                + "VALUES(?,?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, poll_id);
            ps.setString(2, poll.getQuestion());
            ps.setString(3, poll.getStatus());
            ps.setString(4, poll.getTopic());
            ps.setInt(5, user_id);//user_id fk

            ps.executeUpdate();
            ps.close();
        }

        return true;
    }//end

    //Get all active polls
    public ArrayList<Poll> getAllPolls() throws SQLException, ClassNotFoundException {
        String sql = "SELECT p.poll_id, p.question, p.status, p.topic, p.user_id, o.option_id, o.optiontext, o.votecount "
                + "FROM pollhubdb.poll p "
                + "LEFT JOIN pollhubdb.option o ON o.poll_id = p.poll_id "
                + "WHERE p.status = 'Active'";

        HashMap<Integer, Poll> pollMap;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                pollMap = new HashMap<>();
                while (rs.next()) {
                    int pollId = rs.getInt("poll_id");
                    String question = rs.getString("question");
                    String status = rs.getString("status");
                    String topic = rs.getString("topic");
                    int userId = rs.getInt("user_id");

                    Poll poll;
                    if (pollMap.containsKey(pollId)) {
                        poll = pollMap.get(pollId);
                    } else {
                        poll = new Poll();
                        poll.setPollId(pollId);
                        poll.setStatus(status);
                        poll.setQuestion(question);
                        poll.setTopic(topic);
                        poll.setUser_id(userId + "");
                        poll.setPollOptions(new ArrayList<>());  // Initialize the list of options

                        pollMap.put(pollId, poll);
                    }

                    String optionText = rs.getString("optiontext");
                    int voteCount = rs.getInt("votecount");
                    int optionId = rs.getInt("option_id");

                    if (optionText != null) {
                        Option option = new Option(optionText, voteCount);
                        option.setId(optionId);

                        poll.getPollOptions().add(option);
                    }
                }
                rs.close();
            }
            ps.close();
        }
        return new ArrayList<>(pollMap.values());
    }

     public ArrayList<Poll> getMyPolls(String id) throws SQLException, ClassNotFoundException {
         
        String sql = "SELECT p.poll_id, p.question, p.status, p.topic, p.user_id, o.option_id, o.optiontext, o.votecount "
                + "FROM pollhubdb.poll p "
                + "LEFT JOIN pollhubdb.option o ON o.poll_id = p.poll_id "
                + "WHERE p.user_id = ?" ;

        HashMap<Integer, Poll> pollMap;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            
            ps.setInt(1,Integer.parseInt(id));
            
            try (ResultSet rs = ps.executeQuery()) {
                pollMap = new HashMap<>();
                while (rs.next()) {
                    int pollId = rs.getInt("poll_id");
                    String question = rs.getString("question");
                    String status = rs.getString("status");
                    String topic = rs.getString("topic");
                    int userId = rs.getInt("user_id");

                    Poll poll;
                    if (pollMap.containsKey(pollId)) {
                        poll = pollMap.get(pollId);
                    } else {
                        poll = new Poll();
                        poll.setPollId(pollId);
                        poll.setStatus(status);
                        poll.setQuestion(question);
                        poll.setTopic(topic);
                        poll.setUser_id(userId + "");
                        poll.setPollOptions(new ArrayList<>());  // Initialize the list of options

                        pollMap.put(pollId, poll);
                    }

                    String optionText = rs.getString("optiontext");
                    int voteCount = rs.getInt("votecount");
                    int optionId = rs.getInt("option_id");

                    if (optionText != null) {
                        Option option = new Option(optionText, voteCount);
                        option.setId(optionId);

                        poll.getPollOptions().add(option);
                    }
                }
                rs.close();
            }
            ps.close();
        }
        return new ArrayList<>(pollMap.values());
    }//end
    
   //Returns list of current user's polls
    public int getTotalPollVoteCount(int pollId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(VoteCount) total FROM pollhubdb.option "
                + "WHERE Poll_Id = ?";
        int voteCount = 0;

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, pollId);

            try (ResultSet rs = ps.executeQuery()) {
                voteCount = rs.next() ? rs.getInt("total") : 0;

                rs.close();
            }
            ps.close();
        }

        return voteCount;
    }//end

    public boolean deletePoll(int pollId) throws SQLException, ClassNotFoundException {

        String deleteOptions = "DELETE FROM pollhubdb.option WHERE Poll_Id = ?";
        PreparedStatement psO = connection.getConnection().prepareStatement(deleteOptions);
        psO.setInt(1, pollId);
        psO.executeUpdate();

        String deletePoll = "DELETE FROM pollhubdb.poll WHERE Poll_Id = ?";
        PreparedStatement psP = connection.getConnection().prepareStatement(deletePoll);
        psP.setInt(1, pollId);
        psP.executeUpdate();

        return true;
    }

    public boolean updatePollStatus(int pollId, String status) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE pollhubdb.poll "
                + "SET status = ? "
                + "WHERE poll_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, pollId);
            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

}
