package lk.ijse.bo.impl;

import lk.ijse.DTO.EmployeeDTO;
import lk.ijse.bo.EmployeeBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.EmployeeDAO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO, SuperBO {
  EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Employee);


    public  boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getTel()));
    }

    public  boolean deleteEmployee(String id) throws SQLException {
       return employeeDAO.delete(id);
    }

    public  boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {

        return employeeDAO.update(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getTel()));
    }
    public List<EmployeeDTO> getAllEmployee() throws SQLException {
      ArrayList<Employee> employees= (ArrayList<Employee>) employeeDAO.getAll();
     ArrayList<EmployeeDTO> employeeDTOS=new ArrayList<>();
     for (Employee employee:employees){
         EmployeeDTO employeeDTO=new EmployeeDTO(employee.getId(),employee.getName(),employee.getAddress(),employee.getTel());
         employeeDTOS.add(employeeDTO);
     }
     return employeeDTOS;
    }

    public  EmployeeDTO searchEmployee(String tel) throws SQLException {
      Employee employee=employeeDAO.search(tel);
      return new EmployeeDTO(employee.getId(),employee.getName(),employee.getAddress(),employee.getTel());

    }

    public  String getCurrentEmployeeId() throws SQLException {
       return employeeDAO.getCurrentId();
    }

    public int employeeCount() throws SQLException {
      return employeeDAO.employeeCount();
    }
}
