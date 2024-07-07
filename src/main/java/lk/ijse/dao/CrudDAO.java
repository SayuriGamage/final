package lk.ijse.dao;

import lk.ijse.dao.Impl.SQLUtil;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T>  extends SuperDAO{
     boolean save(T dto) throws SQLException ;
      boolean delete(String id) throws SQLException ;
      boolean update(T dto) throws SQLException ;
     List<T> getAll() throws SQLException ;
      String getCurrentId() throws SQLException;
    T search(String od) throws SQLException ;
}
