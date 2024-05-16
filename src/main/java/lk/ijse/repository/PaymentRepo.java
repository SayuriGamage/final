package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getPay_id());
        pstm.setObject(2, payment.getOr_id());
        pstm.setObject(3, payment.getDate());
        pstm.setObject(4, payment.getAmount());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET or_id=?, date=?, amount=? WHERE pay_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getOr_id());
        pstm.setObject(2, payment.getDate());
        pstm.setObject(3, payment.getAmount());
        pstm.setObject(4, payment.getPay_id());

        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String paymentId) throws SQLException {
        String sql = "DELETE FROM payment WHERE pay_id=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, paymentId);

        return pstm.executeUpdate() > 0;
    }

    public static List<Payment> getAllPayments() throws SQLException {
        String sql = "SELECT * FROM payment";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            String paymentId = resultSet.getString("pay_id");
            String orderId = resultSet.getString("or_id");
            String date = resultSet.getString("date");
            double amount = resultSet.getDouble("amount");

            Payment payment = new Payment(paymentId, orderId, date, amount);
            paymentList.add(payment);
        }

        return paymentList;

    }

    public static Payment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM payment WHERE pay_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String paymentId = resultSet.getString("pay_id");
            String orderId = resultSet.getString("or_id");
            String date = resultSet.getString("date");
            double amount = resultSet.getDouble("amount");

            Payment payment = new Payment(paymentId, orderId, date, amount);

            return payment;
        }

        return null;
    }

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT pay_id FROM payment ORDER BY pay_id DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }
}
