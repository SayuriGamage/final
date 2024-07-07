package lk.ijse.dao.Impl;

import lk.ijse.dao.EmployeeDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  boolean save(Employee employee) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?)",employee.getId(),employee.getName(),employee.getAddress(),employee.getTel());
    }

    public  boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE emp_id = ?",id);
    }

    public  boolean update(Employee employee) throws SQLException {

        return SQLUtil.execute("UPDATE employee SET name = ?, job_title = ?, tel = ? WHERE emp_id = ?",employee.getName(),employee.getAddress(),employee.getTel(),employee.getId());
    }
    public  List<Employee> getAll() throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT * FROM employee");
        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Employee employee = new Employee(id, name, address, tel);
            empList.add(employee);
        }
        return empList;
    }

    public  List<String> getIdEmployee() throws SQLException {
        List<String> eqList = new ArrayList<>();
        ResultSet resultSet=SQLUtil.execute("SELECT emp_id FROM employee");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            eqList.add(id);
        }
        return eqList;
    }

    public  Employee search(String tel) throws SQLException {
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM employee WHERE tel=?",tel);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String job = resultSet.getString(3);
            String teln = resultSet.getString(4);
            Employee employee = new Employee(id, name, job, teln);
            return employee;
        }
        return null;
    }

    public  String getCurrentId() throws SQLException {
        ResultSet resultSet=SQLUtil.execute("SELECT emp_id FROM employee ORDER BY emp_id DESC LIMIT 1");
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }

    public int employeeCount() throws SQLException {
        ResultSet resultSet=SQLUtil.execute("SELECT COUNT(*) AS employee_count FROM employee;");
        if(resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;
    }
}