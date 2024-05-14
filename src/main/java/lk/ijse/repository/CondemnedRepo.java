package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Condemned;
import lk.ijse.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondemnedRepo {

    public static boolean save(Condemned condemned) throws SQLException {
        String sql = "INSERT INTO condemn VALUES(?, ?, ?, ?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, condemned.getC_id());
        pstm.setObject(2, condemned.getDetails());
        pstm.setObject(3,condemned.getDate());
        pstm.setObject(4, condemned.getMm_id());

        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM condemn WHERE c_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Condemned condemned) throws SQLException {
        String sql = "UPDATE condemn SET details = ?, date = ? WHERE c_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, condemned.getDetails());
        pstm.setObject(2, condemned.getDate());
        pstm.setObject(3, condemned.getC_id());

        return pstm.executeUpdate() > 0;
    }

    public static Condemned searchById(String id) throws SQLException {
        String sql = "SELECT * FROM condemn WHERE c_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Condemned condemned = new Condemned(cus_id, name, address, tel);

            return condemned;
        }

        return null;
    }

    public static List<Condemned> getAll() throws SQLException {
        String sql = "SELECT * FROM condemn";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Condemned> conList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Condemned condemned = new Condemned(id, name, address, tel);
            conList.add(condemned);
        }
        return conList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT c_id FROM condemn";
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

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT c_id FROM condemn ORDER BY c_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String conId = resultSet.getString(1);
            return conId;
        }
        return null;

    }
}
