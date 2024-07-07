package lk.ijse.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryDAO  extends SuperDAO{
    PreparedStatement getQuery() throws SQLException;

}
