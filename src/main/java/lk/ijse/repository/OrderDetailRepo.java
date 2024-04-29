package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailRepo {
    public static boolean save(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetail od) throws SQLException {
        String sql = "INSERT INTO order_details VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
      //  System.out.println(od.getSp_id()+" "+od.getOr_id());
        pstm.setString(1, od.getOr_id());
        pstm.setString(2, od.getSp_id());
        pstm.setInt(3, od.getQty());
        pstm.setDouble(4, od.getCost());

        return pstm.executeUpdate() > 0;    //false ->  |
    }


}
