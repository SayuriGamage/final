package lk.ijse.bo.impl;

import lk.ijse.bo.SuperBO;
import lk.ijse.bo.UserBO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.dao.UserDAO;
import java.sql.SQLException;

public class UserBOImpl implements UserBO, SuperBO {

    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.User);

    public String Checkcredentialuser(String username) throws SQLException {
       return userDAO.Checkcredential(username);
    }
    public void sendemils(String email) throws SQLException {
         userDAO.sendemils(email);
    }

    public String checktests(String pw) throws SQLException {
      return userDAO.checktests(pw);

    }
}
