package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepo {
    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?)";


       Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getAddress());
        pstm.setObject(4, employee.getTel());

        return pstm.executeUpdate() > 0;
    }
    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, address = ?, tel = ? WHERE id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAddress());
        pstm.setObject(3, employee.getTel());
        pstm.setObject(4, employee.getId());

        return pstm.executeUpdate() > 0;
    }
    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Employee employee = new Employee(cus_id, name, address, tel);

            return employee;
        }

        return null;
    }

}
