package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.placeMaintenance;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceMaintenanceRepo {
    public static boolean placeMaintenance(placeMaintenance po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSaved = MaintenanceRepo.save(po.getMaintenance());
            if (isSaved) {

                    boolean iseqdSaved = equipmentDetailsRepo.save(po.getOsList());
                    if (iseqdSaved) {
                        connection.commit();
                        return true;
                    }
                }


            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    }


