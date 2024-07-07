package lk.ijse.bo;

import lk.ijse.DTO.SparepartsDTO;
import java.sql.SQLException;
import java.util.List;

public interface SparepartsBO  {

       boolean saveParts(SparepartsDTO spareparts) throws SQLException ;

      boolean updateParts(SparepartsDTO spareparts) throws SQLException ;

      boolean deleteParts(String sp_id) throws SQLException ;

      SparepartsDTO getById(String sp_id) throws SQLException ;

     List<SparepartsDTO> getAllParts() throws SQLException ;

      String getCurrentPartsId() throws SQLException ;

     int sparepartsCount() throws SQLException ;
}
