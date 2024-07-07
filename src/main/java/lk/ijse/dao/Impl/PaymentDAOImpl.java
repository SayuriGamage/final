package lk.ijse.dao.Impl;

import lk.ijse.dao.PaymentDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public  boolean save(Payment payment) throws SQLException {

        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?)",payment.getPay_id(),payment.getOr_id(),payment.getDate(),payment.getAmount());
    }

    public  boolean update(Payment payment) throws SQLException {

        return SQLUtil.execute("UPDATE payment SET or_id=?, date=?, amount=? WHERE pay_id=?",payment.getOr_id(),payment.getDate(),payment.getAmount(),payment.getPay_id());
    }

    public  boolean delete(String paymentId) throws SQLException {

        return SQLUtil.execute("DELETE FROM payment WHERE pay_id=?",paymentId);
    }

    public  List<Payment> getAll() throws SQLException {


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");

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

    public  Payment search(String id) throws SQLException {


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE pay_id = ?",id);
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

    public   String getCurrentId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT pay_id FROM payment ORDER BY pay_id DESC LIMIT 1");
        if (resultSet.next()) {
            String empId = resultSet.getString(1);
            return empId;
        }
        return null;
    }
}
