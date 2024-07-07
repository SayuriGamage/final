package lk.ijse.dao.Impl;

import lk.ijse.dao.QueryDAO;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl  implements QueryDAO {
  public   PreparedStatement getQuery() throws SQLException {
     String sql= "SELECT\n" +
              "    DATE_FORMAT(MIN(STR_TO_DATE(p.date, '%Y-%m-%d')), '%Y-%m-%d') AS WeekStartDate,\n" +
              "    DATE_FORMAT(MAX(STR_TO_DATE(p.date, '%Y-%m-%d')), '%Y-%m-%d') AS WeekEndDate,\n" +
              "    COUNT(*) AS WeeklyOrders,\n" +
              "    SUM(p.amount) AS TotalAmount\n" +
              "FROM\n" +
              "    orders o\n" +
              "    INNER JOIN payment p ON o.or_id = p.or_id\n" +
              "WHERE\n" +
              "    STR_TO_DATE(p.date, '%Y-%m-%d') BETWEEN (SELECT MIN(STR_TO_DATE(date, '%Y-%m-%d')) FROM payment) \n" +
              "    AND (SELECT MAX(STR_TO_DATE(date, '%Y-%m-%d')) FROM payment)\n" +
              "GROUP BY\n" +
              "    YEARWEEK(STR_TO_DATE(p.date, '%Y-%m-%d'), 1)\n" +
              "ORDER BY\n" +
              "    WeekStartDate;\n ";
      Connection connection=  DbConnection.getInstance().getConnection();
      PreparedStatement stm=connection.prepareStatement(sql);

      return stm;

    }

}
