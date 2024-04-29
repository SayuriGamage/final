package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderDetail;
import lk.ijse.model.equipmentDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class equipmentDetailsRepo {

    public static boolean save(List<equipmentDetails> osList) throws SQLException {
        for (equipmentDetails os : osList) {
            boolean isSaved = save(os);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    private static boolean save(equipmentDetails os) throws SQLException {
        String sql = "INSERT INTO maintenance_equipment_details VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setString(1,os.getType());
        pstm.setString(2,os.getMm_id());
        pstm.setString(3,os.getEq_id());

        return pstm.executeUpdate() > 0;
    }

}
