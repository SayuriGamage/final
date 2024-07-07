package lk.ijse.dao.Impl;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.UserDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.util.GmailSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO, SuperDAO {
  public String Checkcredential(String username) throws SQLException {
      ResultSet resultSet = SQLUtil.execute("SELECT name,password from user where name=?",username);
      if (resultSet.next()) {
          String dbpw = resultSet.getString("password");
          return dbpw;
      }
      return null;
  }
   public void sendemils(String email) throws SQLException {
       ResultSet resultSet = SQLUtil.execute("SELECT eq_id, name FROM equipment WHERE warranty = CURDATE()");
       List<String> ss = new ArrayList<>();

       while (resultSet.next()) {
           String id = resultSet.getString("eq_id");
           String name=resultSet.getString("name");
           ss.add(id+":"+name);
       }

       GmailSender.setData(ss, email);
    }

  public String checktests(String pw) throws SQLException {
      ResultSet resultSet =SQLUtil.execute("SELECT email from user where password=?",pw);
      if(resultSet.next()){
          String emal=resultSet.getString("email");
         return emal;

      }
      return null;

    }
}
