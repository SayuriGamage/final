package lk.ijse.dao;


import lk.ijse.entity.Orders;


import java.sql.SQLException;

import java.util.List;

public interface OrdersDAO extends CrudDAO<Orders> {
   //   String getCurrentOrderId() throws SQLException ;

     List<String> getOrderIds() throws SQLException;

   //  boolean save(Orders order) throws SQLException ;
}
