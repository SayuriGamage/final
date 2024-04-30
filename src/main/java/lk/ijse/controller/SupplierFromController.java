package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Employee;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.SupplierTm;
import lk.ijse.repository.EmployeeRepo;
import lk.ijse.repository.SupplierRepo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SupplierFromController {

    public Label lblsupdatetim;
    @FXML
    private TableColumn<?, ?> colsup;

    @FXML
    private TableColumn<?, ?> colsupnam;

    @FXML
    private TextField supidtext;

    @FXML
    private TextField supnametext;

    @FXML
    private TableView<SupplierTm> suppliertbl;

    @FXML
    void clearAction(ActionEvent event) {
        clearFields();


    }

    @FXML
    void deleteAction(ActionEvent event) {
        String id = supidtext.getText();

        try {
            boolean isDeleted = SupplierRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                clearFields();
                loadAllSupplier();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveAction(ActionEvent event) {
        String id = supidtext.getText();
        String name = supnametext.getText();


        Supplier supplier = new Supplier(id, name);

        try {
            boolean isSaved = SupplierRepo.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                clearFields();
                loadAllSupplier();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        supidtext.setText("");
        supnametext.setText("");
    }

    @FXML
    void supAction(ActionEvent event) throws SQLException {
        String id =supidtext.getText();

        Supplier supplier=SupplierRepo.searchById(id);
        if (supplier != null) {
           supidtext.setText(supplier.getSup_id());
            supnametext.setText(supplier.getName());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "supplier not found!").show();
        }
    }

    @FXML
    void updateAction(ActionEvent event) {
        String id = supidtext.getText();
        String name = supnametext.getText();


        Supplier supplier = new Supplier(id, name);

        try {
            boolean isUpdated = SupplierRepo.update(supplier);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
                clearFields();
                loadAllSupplier();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void initialize() {
        loadAllSupplier();
        setCellValueFactory();

        setdate();
        suppliertbl.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                SupplierTm selectedSupplier = newSelection;
                supidtext.setText(selectedSupplier.getSup_id());
                supnametext.setText(selectedSupplier.getName());
            }
        });
    }

    private void setdate() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    lblsupdatetim.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
}