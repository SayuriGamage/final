package lk.ijse.dao.Impl;

import lk.ijse.dao.SupplierDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public  boolean save(Supplier supplier) throws SQLException {

        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?,?)",supplier.getSup_id(),supplier.getName(),supplier.getTel());
    }

    public  boolean delete(String id) throws SQLException {

        return SQLUtil.execute("DELETE FROM supplier WHERE sup_id = ?",id);
    }

    public  boolean update(Supplier supplier) throws SQLException {

        return  SQLUtil.execute("UPDATE supplier SET name = ?,tel=? WHERE sup_id = ?",supplier.getName(),supplier.getTel(),supplier.getSup_id());
    }

    public  List<Supplier> getAll() throws SQLException {


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
String tels=resultSet.getString(3);

            Supplier supplier = new Supplier(id, name,tels);
            supList.add(supplier);
        }
        return supList;
    }
    public  Supplier search(String tell) throws SQLException {

        ResultSet resultSet= SQLUtil.execute("SELECT * FROM supplier WHERE tel=?",tell);
        if(resultSet.next()){
            String id=resultSet.getString(1);
            String  name=resultSet.getString(2);
            String tel=resultSet.getString(3);
            Supplier supplier=new Supplier(id,name,tel);
            return  supplier;
        }
        return  null;
    }

    public  List<String> getTellsSupplier() throws SQLException {


        List<String> iList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT tel FROM supplier");
        while (resultSet.next()) {
            String tel = resultSet.getString(1);
            iList.add(tel);
        }
        return iList;
    }

    public  String getCurrentId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT sup_id FROM supplier ORDER BY sup_id DESC LIMIT 1");
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }
}
