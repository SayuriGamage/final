package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.DTO.PaymentDTO;
import lk.ijse.bo.PaymentBO;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.bo.impl.BOTypes;
import lk.ijse.bo.impl.PaymentBOImpl;
import lk.ijse.dao.Impl.OrdersDAOImpl;
import lk.ijse.dao.Impl.PaymentDAOImpl;
import lk.ijse.dao.PaymentDAO;
import lk.ijse.entity.Payment;
import lk.ijse.entity.tm.PaymentTm;

import lk.ijse.util.Regex;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PaymentFormController {

    public TextField paymenttext;
    public TextField datetext;
    public TextField amounttext;
    public TableColumn colpayid;
    public TableColumn colorid;
    public TableColumn colpaydate;
    public TableColumn colpayamount;
    public TableView tblpayment;
    public ComboBox comorpay;
    public Label lbldatepayments;


    PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOTypes.Paymentbo);
    public void initialize() {
        loadAllPayments();
        setCellValueFactory();

        getOrderid();
        setdate();
        tblpayment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                PaymentTm selectedPayment = (PaymentTm) newSelection;
                paymenttext.setText(selectedPayment.getPay_id());
                comorpay.setValue(selectedPayment.getOr_id());
                datetext.setText(selectedPayment.getDate());
                amounttext.setText(String.valueOf(selectedPayment.getAmount()));
            }
        });

        String     currenteqId = null;
        try {
            currenteqId = paymentBO.getCurrentPaymentId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String nextempId = generateNextpayId(currenteqId);
        paymenttext.setText(nextempId);
    }

    private String generateNextpayId(String currenteqId) {
        if (currenteqId != null && currenteqId.matches("^PAY\\d+$")) {

            String numericPart = currenteqId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "PAY" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "PAY001";
    }

    private void setdate() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                   lbldatepayments.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void getOrderid() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            OrdersDAOImpl ordersDAOimpl=new OrdersDAOImpl();
            List<String> orList =ordersDAOimpl.getOrderIds();

            for (String id : orList) {
                obList.add(id);
            }

            comorpay.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colpayid.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
       colorid.setCellValueFactory(new PropertyValueFactory<>("or_id"));
        colpaydate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colpayamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDTO> paymentList = paymentBO.getAllPayment();

            for (PaymentDTO payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getPay_id(),
                        payment.getOr_id(),
                        payment.getDate(),
                        payment.getAmount()
                );

                obList.add(tm);
            }

            tblpayment.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while loading payments: " + e.getMessage()).show();
        }
    }



    public void payonAction(ActionEvent actionEvent) throws SQLException {
        String id = paymenttext.getText();

        PaymentDTO payment = paymentBO.searchPayment(id);
        if (payment != null) {
            paymenttext.setText(payment.getPay_id());

            comorpay.getSelectionModel().select(payment.getOr_id());
            datetext.setText(payment.getDate());
            amounttext.setText(Double.toString(payment.getAmount()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();
        }
    }

    public void savepaymentAction(ActionEvent actionEvent) {
        String paymentId = paymenttext.getText();

        String orderId = comorpay.getValue().toString();
        String date = datetext.getText();
        double amount = Double.parseDouble(amounttext.getText());
      //  Payment payment = new Payment(paymentId, orderId, date, amount);

        try {

            boolean isSaved = paymentBO.savePayment(new PaymentDTO(paymentId, orderId, date, amount));
            if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                    clearFields();
                    loadAllPayments();
                    setCellValueFactory();
                String     currenteqId = paymentBO.getCurrentPaymentId();
                String nextempId = generateNextpayId(currenteqId);
                paymenttext.setText(nextempId);
                }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving payment: " + e.getMessage()).show();
        }
    }

    private void clearFields() {
        paymenttext.clear();

        comorpay.getSelectionModel().clearSelection();
        datetext.clear();
        amounttext.clear();
    }

    public void updatePaymentAction(ActionEvent actionEvent) {
        String paymentId = paymenttext.getText();

        String orderId = comorpay.getValue().toString();
        String date = datetext.getText();
        double amount = Double.parseDouble(amounttext.getText());

      //  Payment payment = new Payment(paymentId, orderId, date, amount);

        try {
            boolean isUpdated = paymentBO.updatePayment(new PaymentDTO(paymentId, orderId, date, amount));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment updated successfully!").show();
                clearFields();
                loadAllPayments();
                setCellValueFactory();
                String     currenteqId = paymentBO.getCurrentPaymentId();
                String nextempId = generateNextpayId(currenteqId);
                paymenttext.setText(nextempId);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update payment!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating payment: " + e.getMessage()).show();
        }
    }

    public void deletepaymentAction(ActionEvent actionEvent) {
        String paymentId = paymenttext.getText();

        try {
            boolean isDeleted = paymentBO.deletePayment(paymentId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted successfully!").show();
                clearFields();
                loadAllPayments();
                setCellValueFactory();
                String     currenteqId = paymentBO.getCurrentPaymentId();
                String nextempId = generateNextpayId(currenteqId);
                paymenttext.setText(nextempId);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete payment!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting payment: " + e.getMessage()).show();
        }
    }

    public void clearpaymentAction(ActionEvent actionEvent) throws SQLException {
clearFields();
        String     currenteqId = paymentBO .getCurrentPaymentId();
        String nextempId = generateNextpayId(currenteqId);
        paymenttext.setText(nextempId);
    }



    public void paymentdate(KeyEvent keyEvent) {
       Regex.setTextColor(lk.ijse.util.TextField.DATE,datetext);
    }

    public void paymentamount(KeyEvent keyEvent) {
Regex.setTextColor(lk.ijse.util.TextField.COST,amounttext);
    }
    public  boolean valid(){

        if(!Regex.setTextColor(lk.ijse.util.TextField.DATE,datetext)) return false;
        if(!Regex.setTextColor(lk.ijse.util.TextField.COST,amounttext))return  false;
        return true;
    }
}
