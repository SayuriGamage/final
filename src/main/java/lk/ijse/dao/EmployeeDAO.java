package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

      List<String> getIdEmployee() throws SQLException ;

    //  Employee searchBytelEmployee(String tel) throws SQLException ;

     int employeeCount() throws SQLException ;
}
