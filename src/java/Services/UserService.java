package Services;

import Data_Access_Objects.UserDAO;
import Entities.User;
import java.sql.SQLException;

public class UserService {

    private final UserDAO userDao;
    private final AccountService accountService;

    public UserService() throws SQLException, ClassNotFoundException {
        userDao = new UserDAO();
        accountService = new AccountService();
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {

        boolean addedUser = userDao.addUser(user);
        if (addedUser) {
            accountService.addAccount(user.getAccount(), user.getUserId());
        }
        return true;
    }//end

    public String getUser(int userId) throws SQLException, ClassNotFoundException {
        return userDao.getUser(userId);
    }//end

    public String getUserId(String username, String password) throws SQLException, ClassNotFoundException {
       return userDao.getUserId(username, password);   
    }
}
