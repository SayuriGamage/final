package lk.ijse.bo;

import lk.ijse.DTO.CondemnedDTO;
import java.sql.SQLException;
import java.util.List;

public interface CondemnedBO {
      boolean saveCondemn(CondemnedDTO condemnedDTO) throws SQLException ;

      boolean deleteCondemn(String id) throws SQLException;

      boolean updateCondemn(CondemnedDTO condemnedDTO) throws SQLException ;

      CondemnedDTO searchCondemn(String id) throws SQLException ;

     List<CondemnedDTO> getAllCondemn() throws SQLException ;

      String getCurrentCondemnId() throws SQLException ;
}
