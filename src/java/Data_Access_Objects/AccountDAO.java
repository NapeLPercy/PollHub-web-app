package Data_Access_Objects;

import java.sql.*;

import Database_Manager.PollHubDB;
import Entities.Account;
import Utils.Random;
import java.sql.SQLException;

public class AccountDAO {

    private final PollHubDB connection;

    public AccountDAO() throws SQLException, ClassNotFoundException {
        connection = new PollHubDB();
    }

    private boolean checkIdExist(int account_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT account_id FROM pollhubdb.account WHERE account_id = ?";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }//end

    public boolean addAccount(Account account, int user_id) throws SQLException, ClassNotFoundException {

        //get account_id
        Random random = new Random();
        int account_id = random.randomizeTableID();

        while (checkIdExist(user_id)) {
            account_id = random.randomizeTableID();
        }

        //add account;
        String sql = "INSERT INTO pollhubdb.account "
                + "VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, account_id);
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setInt(4, user_id);

            ps.executeUpdate();
            ps.close();
        }
        return true;
    }//end

    public boolean validateLogin(Account account) throws SQLException, ClassNotFoundException {

        String sql = "SELECT username, password FROM pollhubdb.account "
                + "WHERE password = ? AND  username = ? ";

        boolean isValid = false;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setString(2, account.getUsername());
            ps.setString(1, account.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = !isValid;
            }
        }

        return isValid;
    }//end

}
