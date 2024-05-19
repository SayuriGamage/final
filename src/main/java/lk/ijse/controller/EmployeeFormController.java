package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.model.Employee;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.EmployeeRepo;
import lk.ijse.repository.OrdersRepo;
import lk.ijse.util.Regex;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeFormController {

    public TextField empnametext;
    public TextField addresemptext;
    public TextField telemptext;
    public TableView tblEmployee;
    public Label dateemptext;
    public Label lblempid;



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
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                EmployeeTm selectedEmployee = (EmployeeTm) newSelection;
                lblempid.setText(selectedEmployee.getId());
                empnametext.setText(selectedEmployee.getName());
                addresemptext.setText(selectedEmployee.getAddress());
                telemptext.setText(selectedEmployee.getTel());
            }
        });
        String currentempId = null;
        try {
            currentempId = EmployeeRepo.getCurrentId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String nextempId = generateNextempId(currentempId);
        lblempid.setText(nextempId);
    }

    private String generateNextempId(String currentempId) {
        if (currentempId != null && currentempId.matches("^EMP\\d+$")) {

            String numericPart = currentempId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "EMP" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "EMP001";
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

            for (int i = 0; i < employeeList.size(); i++) {
                Employee employee = employeeList.get(i);
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
        String id = lblempid.getText();
        String name = empnametext.getText();
        String address = addresemptext.getText();
        String tel = telemptext.getText();

        Employee employee = new Employee(id, name, address, tel);

        try {
            if(isValied()) {
                boolean isSaved = EmployeeRepo.save(employee);

                if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();

                    clearFields();
                    loadAllCustomers();
                    setCellValueFactory();
                  String   currentempId = EmployeeRepo.getCurrentId();
                    String nextempId = generateNextempId(currentempId);
               lblempid.setText(nextempId);
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "wrong informations!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        lblempid.setText("");
        empnametext.setText("");
        addresemptext.setText("");
        telemptext.setText("");
    }

    public void deleteempAction(ActionEvent actionEvent) {
        String id = lblempid.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
                String   currentempId = EmployeeRepo.getCurrentId();
                String nextempId = generateNextempId(currentempId);
                lblempid.setText(nextempId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void updateempAction(ActionEvent actionEvent) {
        String id = lblempid.getText();
        String name = empnametext.getText();
        String address = addresemptext.getText();
        String tel = telemptext.getText();

        Employee employee = new Employee(id, name, address, tel);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
                String   currentempId = EmployeeRepo.getCurrentId();
                String nextempId = generateNextempId(currentempId);
               lblempid.setText(nextempId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearempAction(ActionEvent actionEvent) throws SQLException {
        clearFields();
        String   currentempId = EmployeeRepo.getCurrentId();
        String nextempId = generateNextempId(currentempId);
        lblempid.setText(nextempId);
    }

    public void employetelAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.CONTACT, telemptext);
    }

    public boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.util.TextField.TEXT, addresemptext)) return  false;
        if(!Regex.setTextColor(lk.ijse.util.TextField.CONTACT,telemptext)) return  false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.NAME,empnametext)) return false;
        return  true;
    }

    public void employeenameAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME,empnametext);
    }

    public void telsearchAction(ActionEvent actionEvent) throws SQLException {
        String tel=telemptext.getText();
        Employee employee=EmployeeRepo.searchBytel(tel);
        if (employee!=null){
            lblempid.setText(employee.getId());
            empnametext.setText(employee.getName());
            addresemptext.setText(employee.getAddress());
            telemptext.setText(employee.getTel());
        }else{
            new Alert(Alert.AlertType.ERROR,"employee not found!").show();
        }
    }

    public void regetitelAction(KeyEvent keyEvent) {
Regex.setTextColor(lk.ijse.util.TextField.TEXT,addresemptext);
    }
}
