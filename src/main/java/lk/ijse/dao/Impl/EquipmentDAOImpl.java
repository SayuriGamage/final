package lk.ijse.dao.Impl;

import lk.ijse.dao.EquipmentDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements EquipmentDAO{
    public  boolean save(Equipment equipment) throws SQLException {

        return SQLUtil.execute("INSERT INTO equipment VALUES (?,?,?,?,?,?,?,?)",equipment.getEq_id(),equipment.getName(),equipment.getModel(),equipment.getCost(),equipment.getPurchase(),equipment.getWarranty(),equipment.getManufacture(),equipment.getUser_id());
    }
    public  boolean update(Equipment equipment) throws SQLException {

        return SQLUtil.execute("UPDATE equipment SET name=?, model=?, cost=?, purchase=?, warranty=?, manufacture=?, user_id=? WHERE eq_id=?",equipment.getName(),equipment.getModel(),equipment.getCost(),equipment.getPurchase(),equipment.getWarranty(),equipment.getManufacture(),equipment.getUser_id(),equipment.getEq_id());
    }

    public  boolean delete(String id) throws SQLException {

        return SQLUtil.execute("DELETE FROM equipment WHERE eq_id = ?",id);
    }
    public  List<Equipment> getAll() throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT * FROM equipment");

        List<Equipment> equipmentList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("eq_id");
            String name = resultSet.getString("name");
            String model = resultSet.getString("model");
            double cost = resultSet.getDouble("cost");
            String purchase = resultSet.getString("purchase");
            String warranty = resultSet.getString("warranty");
            String manufacturer = resultSet.getString("manufacture");
            String userId = resultSet.getString("user_id");

            Equipment equipment = new Equipment(id, name, model, cost, purchase, warranty, manufacturer, userId);
            equipmentList.add(equipment);
        }

        return equipmentList;
    }

    public  Equipment search(String id) throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT * FROM equipment WHERE eq_id = ?",id);

            if (resultSet.next()) {
                String eqId = resultSet.getString("eq_id");
                String name = resultSet.getString("name");
                String model = resultSet.getString("model");
                double cost = resultSet.getDouble("cost");
                String purchase = resultSet.getString("purchase");
                String warranty = resultSet.getString("warranty");
                String manufacturer = resultSet.getString("manufacture");
                String userId = resultSet.getString("user_id");

                return new Equipment(eqId, name, model, cost, purchase, warranty, manufacturer, userId);
            }

        return null;
    }

    public  List<String> getEquipmentIds() throws SQLException {


        List<String> idList = new ArrayList<>();


        ResultSet resultSet=SQLUtil.execute("SELECT eq_id FROM equipment");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public  String getCurrentId() throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT eq_id FROM equipment ORDER BY eq_id DESC LIMIT 1");
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }

    public int equipmentCount() throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT COUNT(*) AS equipment_count FROM equipment;");

        if(resultSet.next()) {
            return resultSet.getInt("equipment_count");
        }
        return 0;
    }


}
