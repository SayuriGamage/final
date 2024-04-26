package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Orders;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepo {


    public static String getCurrentId() throws SQLException {
        String sql = "SELECT or_id FROM orders ORDER BY or_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT or_id FROM orders";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> orsList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            orsList.add(id);
        }
        return orsList;
    }
    public static boolean save(Orders order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, order.getOr_id());
        pstm.setDate(2, Date.valueOf(order.getOrder_date()));
        pstm.setString(3, order.getSup_id());


        return pstm.executeUpdate() > 0;
    }
}

