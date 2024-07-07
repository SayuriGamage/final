package lk.ijse.dao.Impl;

import lk.ijse.dao.OrdersDAO;

import lk.ijse.entity.Orders;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {


    public  String getCurrentId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT or_id FROM orders ORDER BY or_id DESC LIMIT 1");
        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    @Override
    public Orders search(String od) throws SQLException {
        return null;
    }

    public  List<String> getOrderIds() throws SQLException {
        List<String> orsList = new ArrayList<>();

        ResultSet resultSet=SQLUtil.execute("SELECT or_id FROM orders");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            orsList.add(id);
        }
        return orsList;
    }
    public  boolean save(Orders order) throws SQLException {

        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?)",order.getOr_id(),order.getOrder_date(),order.getSup_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Orders dto) throws SQLException {
        return false;
    }

    @Override
    public List<Orders> getAll() throws SQLException {
        return List.of();
    }
}

