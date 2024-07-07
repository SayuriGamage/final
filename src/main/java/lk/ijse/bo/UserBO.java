package lk.ijse.bo;

import lk.ijse.dao.Impl.SQLUtil;
import lk.ijse.util.GmailSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO {
     String Checkcredentialuser(String username) throws SQLException ;

     void sendemils(String email) throws SQLException ;

     String checktests(String pw) throws SQLException;
}
