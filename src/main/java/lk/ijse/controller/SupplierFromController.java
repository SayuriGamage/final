package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Employee;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.model.tm.SupplierTm;
import lk.ijse.repository.EmployeeRepo;
import lk.ijse.repository.SupplierRepo;

import java.sql.SQLException;
import java.util.List;

public class SupplierFromController {

    public TextField supidtext;
    public TextField supnametext;
    public TableView suppliertbl;
    public TableColumn colsup;
    public TableColumn colsupnam;
    public void initialize() {
        setCellValueFactory();
        loadAllSupplier();
    }

    private void setCellValueFactory() {
       colsup.setCellValueFactory(new PropertyValueFactory<>("sup_id"));
        colsupnam.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();

            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSup_id(),
                       supplier.getName()

                );

                obList.add(tm);
            }

            suppliertbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAction(ActionEvent actionEvent) {
        String id = supidtext.getText();
        String name = supnametext.getText();


        Supplier supplier = new Supplier(id, name);

        try {
            boolean isSaved = SupplierRepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        supidtext.setText("");
      supnametext.setText("");
    }

    public void deleteAction(ActionEvent actionEvent) {
        String id = supidtext.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateAction(ActionEvent actionEvent) {
        String id = supidtext.getText();
        String name = supnametext.getText();


       Supplier supplier = new Supplier(id, name);

        try {
            boolean isUpdated = SupplierRepo.update(supplier);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearAction(ActionEvent actionEvent) {
clearFields();
    }
}
