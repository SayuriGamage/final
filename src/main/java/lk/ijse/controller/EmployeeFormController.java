package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.model.Employee;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeFormController {

    public TextField empidtext;
    public TextField empnametext;
    public TextField addresemptext;
    public TextField telemptext;
    public TableView tblEmployee;
    public Label dateemptext;
    public Label datelblemp;



    private AnchorPane dashboardpane;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    public void initialize() {
       setCellValueFactory();
        loadAllCustomers();
        setDate();

    }

    private void setDate() {

      
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    dateemptext.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllCustomers() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();

            for (Employee employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getId(),
                        employee.getName(),
                        employee.getAddress(),
                        employee.getTel()
                );

                obList.add(tm);
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveempAction(ActionEvent actionEvent) {
        String id = empidtext.getText();
        String name = empnametext.getText();
        String address = addresemptext.getText();
        String tel = telemptext.getText();

        Employee employee = new Employee(id, name, address, tel);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        empidtext.setText("");
        empnametext.setText("");
        addresemptext.setText("");
        telemptext.setText("");
    }

    public void deleteempAction(ActionEvent actionEvent) {
        String id = empidtext.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateempAction(ActionEvent actionEvent) {
        String id = empidtext.getText();
        String name = empnametext.getText();
        String address = addresemptext.getText();
        String tel = telemptext.getText();

        Employee employee = new Employee(id, name, address, tel);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearempAction(ActionEvent actionEvent) {
        clearFields();
    }





    public void textSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = empidtext.getText();

        Employee employee = EmployeeRepo.searchById(id);
        if (employee != null) {
            empidtext.setText(employee.getId());
            empnametext.setText(employee.getName());
            addresemptext.setText(employee.getAddress());
            telemptext.setText(employee.getTel());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }
}
