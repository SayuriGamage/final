package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Maintenance;
import lk.ijse.model.OrderDetail;
import lk.ijse.model.equipmentDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class equipmentDetailsRepo {

    public static boolean save(List<equipmentDetails> osList) throws SQLException {
        for (equipmentDetails os : osList) {
            boolean isSaved = save(os);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(equipmentDetails os) throws SQLException {
        String sql = "INSERT INTO maintenance_equipment_details VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1, os.getType());
        pstm.setString(2, os.getMm_id());
        pstm.setString(3, os.getEq_id());

        return pstm.executeUpdate() > 0;
    }


    public static boolean updateequipmentDetails(equipmentDetails equipmentDetails) throws SQLException {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            String sql = "UPDATE maintenance_equipment_details SET type=?, eq_id=? WHERE mm_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, equipmentDetails.getType());
                preparedStatement.setString(2, equipmentDetails.getEq_id());
                preparedStatement.setString(3, equipmentDetails.getMm_id());

                int affectedRows = preparedStatement.executeUpdate();

                return affectedRows > 0;
            }
        }
    }

}
