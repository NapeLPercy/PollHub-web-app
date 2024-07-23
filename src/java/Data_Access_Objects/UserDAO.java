package Data_Access_Objects;

import Database_Manager.PollHubDB;
import Entities.User;
import Utils.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final PollHubDB connection;

    public UserDAO() throws SQLException, ClassNotFoundException {
        connection = new PollHubDB();
    }

    public boolean checkIdExist(int user_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT account_id FROM pollhubdb.account WHERE account_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }//end

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {

        //get user_id
        Random random = new Random();
        int user_id = random.randomizeTableID();

        while (checkIdExist(user_id)) {
            user_id = random.randomizeTableID();
        }

        String sql = "INSERT INTO pollhubdb.user "
                + "VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, user_id);
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getEmail());

            user.setUserId(user_id);//set user id so it can be fk for account

            ps.executeUpdate();
            ps.close();

        }
        return true;
    }//end

    public String getUser(int userId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT Last_Name, First_Name FROM pollhubdb.user WHERE user_id = ?";

        String user = "";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = rs.getString("Last_Name") + " " + rs.getString("First_Name");
                }
                rs.close();
            }
            ps.close();
        }

        return user;
    }//end

    public String getUserId(String username, String password) throws SQLException, ClassNotFoundException {

        String sql = "SELECT user_id FROM pollhubdb.account "
                + "WHERE username = ? AND password = ? ";

        String userId = "";
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    userId = rs.getString("user_id");
                }
                rs.close();
            }
            ps.close();
        }
        return userId;
    }//end
}
