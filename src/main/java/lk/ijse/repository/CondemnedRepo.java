package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Condemned;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondemnedRepo {

    private static final String INSERT_SQL = "INSERT INTO condemn VALUES(?, ?, ?, ?)";
    private static final String DELETE_SQL = "DELETE FROM condemn WHERE c_id = ?";
    private static final String UPDATE_SQL = "UPDATE condemn SET details = ?, date = ?, mm_id = ? WHERE c_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM condemn";
    private static final String SELECT_IDS_SQL = "SELECT c_id FROM condemn";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM condemn WHERE c_id = ?";

    public static boolean save(Condemned condemned) {
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(INSERT_SQL)) {

            pstm.setObject(1, condemned.getC_id());
            pstm.setObject(2, condemned.getDetails());
            pstm.setObject(3, condemned.getDate());
            pstm.setObject(4, condemned.getMm_id());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false;
        }
    }

    public static boolean delete(String id) {
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(DELETE_SQL)) {

            pstm.setObject(1, id);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false;
        }
    }

    public static boolean update(Condemned condemned) {
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(UPDATE_SQL)) {

            pstm.setObject(1, condemned.getDetails());
            pstm.setObject(2, condemned.getDate());
            pstm.setObject(3, condemned.getMm_id());
            pstm.setObject(4, condemned.getC_id());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false;
        }
    }

    public static List<Condemned> getAll() {
        List<Condemned> condemnedList = new ArrayList<>();
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String details = resultSet.getString(2);
String date=resultSet.getString(3);
                String mm_id = resultSet.getString(4);

                Condemned condemned = new Condemned(id, details,date, mm_id);
                condemnedList.add(condemned);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return condemnedList;
    }

    public static List<String> getIds() {
        List<String> idList = new ArrayList<>();
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(SELECT_IDS_SQL);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                idList.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return idList;
    }

    public static Condemned searchById(String id) {
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(SELECT_BY_ID_SQL)) {

            pstm.setObject(1, id);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    String cus_id = resultSet.getString(1);
                    String details = resultSet.getString(2);
                    String date = resultSet.getString(3);
                    String mm_id = resultSet.getString(4);

                    return new Condemned(cus_id, details, date, mm_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return null;
    }
}
