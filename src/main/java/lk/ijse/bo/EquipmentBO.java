package lk.ijse.bo;
import lk.ijse.DTO.EquipmentDTO;
import java.sql.SQLException;
import java.util.List;

public interface EquipmentBO {
      boolean saveEquipment(EquipmentDTO equipmentDTO) throws SQLException ;

      boolean updateEquipment(EquipmentDTO equipmentDTO) throws SQLException ;

      boolean deleteEquipment(String id) throws SQLException ;

     List<EquipmentDTO> getAllEquipment() throws SQLException ;

      EquipmentDTO searchEquipment(String id) throws SQLException ;

      String getCurrentEquipmentId() throws SQLException ;

     int equipmentCount() throws SQLException ;

}
