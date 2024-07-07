package lk.ijse.bo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryBO {
     PreparedStatement getQuery() throws SQLException;
}
