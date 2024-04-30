package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderDetail;
import lk.ijse.model.Spareparts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SparepartsRepo {
    public static boolean save(Spareparts spareparts) throws SQLException {
        String sql = "INSERT INTO spareparts VALUES(?, ?, ?, ?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, spareparts.getSp_id());
        pstm.setObject(2, spareparts.getName());
        pstm.setObject(3, spareparts.getManufacture());
        pstm.setDouble(4, spareparts.getCost()); // Assuming getCost() returns a double
        pstm.setInt(5, spareparts.getQty());
        pstm.setObject(6, spareparts.getPurchase());
        pstm.setObject(7, spareparts.getMm_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Spareparts spareparts) throws SQLException {
        String sql = "UPDATE spareparts SET name=?, manufacture=?, cost=?, qty=?, purchase=?, mm_id=? WHERE sp_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, spareparts.getName());
        pstm.setObject(2, spareparts.getManufacture());
        pstm.setDouble(3, spareparts.getCost());
        pstm.setInt(4, spareparts.getQty());
        pstm.setObject(5, spareparts.getPurchase());
        pstm.setObject(6, spareparts.getMm_id());
        pstm.setObject(7, spareparts.getSp_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String sp_id) throws SQLException {
        String sql = "DELETE FROM spareparts WHERE sp_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, sp_id);

        return pstm.executeUpdate() > 0;
    }

    public static Spareparts getById(String sp_id) throws SQLException {
        String sql = "SELECT * FROM spareparts WHERE sp_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, sp_id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String id = resultSet.getString("sp_id");
            String name = resultSet.getString("name");
            String manufacture = resultSet.getString("manufacture");
            double cost = resultSet.getDouble("cost");
            int qty = resultSet.getInt("qty");
            String purchase = resultSet.getString("purchase");
            String mm_id = resultSet.getString("mm_id");

            return new Spareparts(id, name, manufacture, cost, qty, purchase, mm_id);
        }
        return null;
    }

    public static List<Spareparts> getAll() throws SQLException {
        String sql = "SELECT * FROM spareparts";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Spareparts> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String manu = resultSet.getString(3);
            double cost = resultSet.getDouble(4);
            int qty = resultSet.getInt(5);
            String purch = resultSet.getString(6);
            String mid = resultSet.getString(7);

            Spareparts spareparts = new Spareparts(id, name, manu, cost, qty, purch, mid);
            supList.add(spareparts);
        }
        return supList;
    }

    public static List<String> getCodes() throws SQLException {
        String sql = "SELECT sp_id FROM  spareparts";
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection()
                .prepareStatement(sql)
                .executeQuery();

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public static Spareparts searchById(String code) throws SQLException {
        String sql = "SELECT * FROM spareparts WHERE sp_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, code);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String id = resultSet.getString("sp_id");
            String name = resultSet.getString("name");
            String manufacture = resultSet.getString("manufacture");
            double cost = resultSet.getDouble("cost");
            int qty = resultSet.getInt("qty");
            String purchase = resultSet.getString("purchase");
            String mm_id = resultSet.getString("mm_id");

            return new Spareparts(id, name, manufacture, cost, qty, purchase, mm_id);
        }
        return null;
    }
    public static boolean update(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od: odList) {


            boolean isUpdateQty = updateQty(od.getSp_id(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String code, int qty) throws SQLException {
        String sql = "UPDATE spareparts SET qty= qty - ? WHERE sp_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        System.out.println(code);
        pstm.setInt(1, qty);
        pstm.setString(2,code );


        return pstm.executeUpdate() > 0;
    }


}
