package lk.ijse.dao.Impl;

import lk.ijse.dao.SparepartsDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Spareparts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SparepartsDAOImpl implements SparepartsDAO {
    public  boolean save(Spareparts spareparts) throws SQLException {

        return SQLUtil.execute("INSERT INTO spareparts VALUES(?, ?, ?, ?,?,?,?)",spareparts.getSp_id(),spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id());
    }

    public  boolean update(Spareparts spareparts) throws SQLException {

        return SQLUtil.execute("UPDATE spareparts SET name=?, manufacture=?, cost=?, qty=?, purchase=?, mm_id=? WHERE sp_id=?",spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id(),spareparts.getSp_id());
    }

    public  boolean delete(String sp_id) throws SQLException {

        return SQLUtil.execute("DELETE FROM spareparts WHERE sp_id=?",sp_id);
    }

    public  Spareparts getById(String sp_id) throws SQLException {


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM spareparts WHERE sp_id=?",sp_id);
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

    public  List<Spareparts> getAll() throws SQLException {


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM spareparts");

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

    public  List<String> getSparepartsCodes() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT sp_id FROM  spareparts");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public  Spareparts search(String code) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM spareparts WHERE sp_id = ?",code);
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
    public    boolean updateqty(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od: odList) {

            if (SQLUtil.execute("UPDATE spareparts SET qty= qty - ? WHERE sp_id = ?",od.getQty(),od.getSp_id() )) {
                return true;
            }
        }
        return false;
    }




    public  String getCurrentId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT sp_id FROM spareparts ORDER BY sp_id DESC LIMIT 1");
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }

    public int sparepartsCount() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT SUM(qty) AS total_qty FROM spareparts;");

        if(resultSet.next()) {
            return resultSet.getInt("total_qty");
        }
        return 0;
    }
}
