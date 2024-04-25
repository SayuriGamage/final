package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepo {
    public static boolean save(Equipment equipment) throws SQLException {
        String sql = "INSERT INTO equipment (eq_id, name, model, cost, purchase, warranty, manufacture, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, equipment.getEq_id());
        pstm.setString(2, equipment.getName());
        pstm.setString(3, equipment.getModel());
        pstm.setDouble(4, equipment.getCost());
        pstm.setString(5, equipment.getPurchase());
        pstm.setString(6, equipment.getWarranty());
        pstm.setString(7, equipment.getManufacture());
        pstm.setString(8, equipment.getUser_id());

        return pstm.executeUpdate() > 0;
    }
    public static boolean update(Equipment equipment) throws SQLException {
        String sql = "UPDATE equipment SET name=?, model=?, cost=?, purchase=?, warranty=?, manufacture=?, user_id=? WHERE eq_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, equipment.getName());
        pstm.setObject(2, equipment.getModel());
        pstm.setDouble(3, equipment.getCost());
        pstm.setObject(4, equipment.getPurchase());
        pstm.setObject(5, equipment.getWarranty());
        pstm.setObject(6, equipment.getManufacture());
        pstm.setObject(7, equipment.getUser_id());
        pstm.setObject(8, equipment.getEq_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM equipment WHERE eq_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static List<Equipment> getAll() throws SQLException {
        String sql = "SELECT * FROM equipment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

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
    }public static Equipment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM equipment WHERE eq_id = ?";
        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            ResultSet resultSet = pstm.executeQuery();

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
        } catch (SQLException ex) {
            throw new SQLException("Error occurred while searching equipment by ID: " + ex.getMessage(), ex);
        }

        return null;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT eq_id FROM equipment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
