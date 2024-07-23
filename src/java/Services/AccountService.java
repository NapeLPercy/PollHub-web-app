package Services;

import Data_Access_Objects.AccountDAO;
import Entities.Account;
import java.sql.SQLException;

public class AccountService {

    private final AccountDAO accountDao;

    public AccountService() throws SQLException, ClassNotFoundException {
        accountDao = new AccountDAO();
    }
    
    public boolean addAccount(Account account, int user_id) throws SQLException, ClassNotFoundException{
      return accountDao.addAccount(account, user_id);    
    }//end
    
       public boolean validateLogin(Account account) throws SQLException, ClassNotFoundException{
       return accountDao.validateLogin(account);
       }//end
    
}
