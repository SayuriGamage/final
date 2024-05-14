package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier VALUES(?, ?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getSup_id());
        pstm.setObject(2, supplier.getName());
pstm.setObject(3,supplier.getTel());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE sup_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET name = ?,tel=? WHERE sup_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
pstm.setObject(2,supplier.getTel());
        pstm.setObject(3, supplier.getSup_id());
        return pstm.executeUpdate() > 0;
    }

    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

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
    public static Supplier searchBytell(String tell) throws SQLException {
        String sql="SELECT * FROM supplier WHERE tel=?";
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setObject(1,tell);
        ResultSet resultSet=pstm.executeQuery();
        if(resultSet.next()){
            String id=resultSet.getString(1);
            String  name=resultSet.getString(2);
            String tel=resultSet.getString(3);
            Supplier supplier=new Supplier(id,name,tel);
            return  supplier;
        }
        return  null;
    }

    public static List<String> getTells() throws SQLException {
        String sql = "SELECT tel FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> iList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String tel = resultSet.getString(1);
            iList.add(tel);
        }
        return iList;
    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT sup_id FROM supplier ORDER BY sup_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }
}
