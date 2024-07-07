package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.Supplier;


import java.sql.SQLException;

import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {


      List<String> getTellsSupplier() throws SQLException;


}
