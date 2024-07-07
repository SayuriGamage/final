package lk.ijse.dao;

import java.sql.SQLException;

public interface UserDAO {
    String Checkcredential(String username) throws SQLException;

    void sendemils(String email) throws SQLException;

    String checktests(String pw) throws SQLException;

}
