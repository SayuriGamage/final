package lk.ijse.bo.impl;

import lk.ijse.DTO.placeMaintenanceDTO;
import lk.ijse.bo.MaintenanceBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.EmployeeDAO;
import lk.ijse.dao.EquipmentDAO;
import lk.ijse.dao.EquipmentDetailsDAO;
import lk.ijse.dao.Impl.*;
import lk.ijse.dao.MaintenanceDAO;
import lk.ijse.db.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MaintenanaceBOImpl implements MaintenanceBO, SuperBO {

    MaintenanceDAO maintenanceDAO= (MaintenanceDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Maintenance);
    EquipmentDetailsDAO equipmentDetailsDAO= (EquipmentDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.EquipmentDetails);
    EquipmentDAO equipmentDAO= (EquipmentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Equipment);
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Employee);

    public  String getMaintenanceId( String description, double cost) throws SQLException {
        return maintenanceDAO.getMaintenanceId(description,cost);
    }
    public  String getMaintenanceempId( String description, double cost) throws SQLException {

        return maintenanceDAO.getMaintenanceempId(description,cost);
    }

    public  String getCurrentMaintenanceId() throws SQLException {
        return maintenanceDAO.getCurrentId();
    }
    public  List<String> getEquipmentIds() throws SQLException {
      return   equipmentDAO.getEquipmentIds();
    }
    public  List<String> getIdEmployee() throws SQLException {
        return employeeDAO.getIdEmployee();
    }
    public boolean placeMaintenances(placeMaintenanceDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {

            boolean isSaved = maintenanceDAO.save(po.getMaintenance());
            if (isSaved) {

                boolean iseqdSaved = equipmentDetailsDAO.save(po.getOsList());
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
    public  List<String> getMaintenanceIds() throws SQLException {
        return maintenanceDAO.getMaintenanceIds();
    }

}
