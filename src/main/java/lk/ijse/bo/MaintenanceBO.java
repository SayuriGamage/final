package lk.ijse.bo;

import lk.ijse.DTO.placeMaintenanceDTO;
import java.sql.SQLException;
import java.util.List;

public interface MaintenanceBO {
      String getMaintenanceId( String description, double cost) throws SQLException ;
      String getMaintenanceempId( String description, double cost) throws SQLException;
      String getCurrentMaintenanceId() throws SQLException ;
      List<String> getEquipmentIds() throws SQLException;
      List<String> getIdEmployee() throws SQLException ;
     boolean placeMaintenances(placeMaintenanceDTO po) throws SQLException ;
      List<String> getMaintenanceIds() throws SQLException;
}
