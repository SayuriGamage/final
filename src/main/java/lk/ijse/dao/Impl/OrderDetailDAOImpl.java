package lk.ijse.dao.Impl;

import lk.ijse.dao.OrderDetailDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public  boolean save(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {

            if (SQLUtil.execute("INSERT INTO order_details VALUES(?, ?, ?, ?)",od.getOr_id(),od.getSp_id(),od.getQty(),od.getCost()) ){
                return true;
            }
        }
        return false;
    }



    public boolean save(OrderDetail dto) throws SQLException {
        return false;
    }


    public boolean delete(String id) throws SQLException {
        return false;
    }


    public boolean update(OrderDetail dto) throws SQLException {
        return false;
    }


    public List<OrderDetail> getAll() throws SQLException {
        return List.of();
    }

    public String getCurrentId() throws SQLException {
        return "";
    }


    public OrderDetail search(String od) throws SQLException {
        return null;
    }
}
