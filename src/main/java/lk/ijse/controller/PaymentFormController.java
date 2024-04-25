package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Employee;
import lk.ijse.model.Payment;
import lk.ijse.model.tm.PaymentTm;
import lk.ijse.repository.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PaymentFormController {

    public TextField paymenttext;
    public TextField ordertext;
    public TextField datetext;
    public TextField amounttext;
    public TableColumn colpayid;
    public TableColumn colorid;
    public TableColumn colpaydate;
    public TableColumn colpayamount;
    public TableView tblpayment;
    public ComboBox comorpay;
    public Label lbldatepayments;


    public void initialize() {
        setCellValueFactory();
        loadAllPayments();
        getOrderid();
        setdate();
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
            List<String> orList = OrdersRepo.getIds();

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
            List<Payment> paymentList = PaymentRepo.getAllPayments();

            for (Payment payment : paymentList) {
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

        Payment payment = PaymentRepo.searchById(id);
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
        Payment payment = new Payment(paymentId, orderId, date, amount);

        try {

            boolean isSaved = PaymentRepo.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();
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

        Payment payment = new Payment(paymentId, orderId, date, amount);

        try {
            boolean isUpdated = PaymentRepo.update(payment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment updated successfully!").show();
                clearFields();
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
            boolean isDeleted = PaymentRepo.delete(paymentId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete payment!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting payment: " + e.getMessage()).show();
        }
    }

    public void clearpaymentAction(ActionEvent actionEvent) {
clearFields();
    }
}
