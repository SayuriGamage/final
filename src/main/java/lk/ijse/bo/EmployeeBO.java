package lk.ijse.bo;

import lk.ijse.DTO.EmployeeDTO;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO {
      boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException ;

      boolean deleteEmployee(String id) throws SQLException ;

      boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException ;

     List<EmployeeDTO> getAllEmployee() throws SQLException ;

      EmployeeDTO searchEmployee(String tel) throws SQLException ;

      String getCurrentEmployeeId() throws SQLException ;

     int employeeCount() throws SQLException ;
}
