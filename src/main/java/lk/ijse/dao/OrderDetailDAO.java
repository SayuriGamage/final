package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

      boolean save(List<OrderDetail> odList) throws SQLException ;
}
