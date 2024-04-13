package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.Employee;
import lk.ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {

    public TextField empidtext;
    public TextField empnametext;
    public TextField addresemptext;
    public TextField telemptext;
    private AnchorPane dashboardpane;



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

    }

    public void empbackAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }
    void btnClearOnAction(ActionEvent event) {
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
