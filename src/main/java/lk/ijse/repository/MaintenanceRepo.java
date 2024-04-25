package lk.ijse.repository;

import lk.ijse.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
