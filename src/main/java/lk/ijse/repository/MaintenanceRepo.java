package lk.ijse.repository;


import lk.ijse.db.DbConnection;
import lk.ijse.model.Maintenance;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepo {


    public static String getCurrentId() throws SQLException {
        String sql = "SELECT mm_id FROM maintenance ORDER BY mm_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String mainId = resultSet.getString(1);
            return mainId;
        }
        return null;
    }


    public static List<String> getIds() throws SQLException {
        String sql = "SELECT mm_id FROM maintenance";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> eqList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            eqList.add(id);
        }
        return eqList;
    }


    public static boolean save(Maintenance maintenance) throws SQLException {
        String sql = "INSERT INTO maintenance VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, maintenance.getMm_id());
        pstm.setDate(2, Date.valueOf(maintenance.getDate()));
        pstm.setString(3, maintenance.getDescription());
        pstm.setString(4, String.valueOf(maintenance.getCost()));
        pstm.setString(5,maintenance.getEmp_id());


        return pstm.executeUpdate() > 0;
    }


}

