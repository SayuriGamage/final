package lk.ijse.controller;

import com.sun.javafx.charts.Legend;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Employee;
import lk.ijse.model.Equipment;
import lk.ijse.model.tm.EquipmentTm;
import lk.ijse.repository.EmployeeRepo;
import lk.ijse.repository.EquipmentRepo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EquipmentFormController {

    public TextField eqidtext;
    public TextField eqnametext;
    public TextField eqmodltext;
    public TextField eqcosttext;
    public TextField eqpurtext;
    public TextField eqwartext;
    public TextField eqmanutext;
    public TextField equseridtext;
    public TableColumn coleqid;
    public TableColumn coleqname;
    public TableColumn coleqmod;
    public TableColumn coleqcost;
    public TableColumn colwqpur;
    public TableColumn coleqwarr;
    public TableColumn coleqmanu;
    public TableColumn colequserid;
    public TableView tblequipment;
    public Label lbleqdatetime;



    public void initialize() {

        loadAllEquipment();
        setCellValueFactory();
        setDate();
        tblequipment.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                EquipmentTm selectedEquipment = (EquipmentTm) newSelection;
                eqidtext.setText(selectedEquipment.getEq_id());
                eqnametext.setText(selectedEquipment.getName());
                eqmodltext.setText(selectedEquipment.getModel());
                eqcosttext.setText(String.valueOf(selectedEquipment.getCost()));
                eqpurtext.setText(selectedEquipment.getPurchase());
                eqwartext.setText(selectedEquipment.getWarranty());
                eqmanutext.setText(selectedEquipment.getManufacture());
                equseridtext.setText(selectedEquipment.getUser_id());
            }
        });
    }

    private void setDate() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    lbleqdatetime.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setCellValueFactory() {
        coleqid.setCellValueFactory(new PropertyValueFactory<>("eq_id"));
        coleqname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coleqmod.setCellValueFactory(new PropertyValueFactory<>("model"));
        coleqcost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colwqpur.setCellValueFactory(new PropertyValueFactory<>("purchase"));
        coleqwarr.setCellValueFactory(new PropertyValueFactory<>("warranty"));
        coleqmanu.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        colequserid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    }

    private void loadAllEquipment() {
        ObservableList<EquipmentTm> obList = FXCollections.observableArrayList();

        try {
            List<Equipment> equipmentList = EquipmentRepo.getAll();

            for (Equipment equipment : equipmentList) {
                EquipmentTm tm = new EquipmentTm(
                        equipment.getEq_id(),
                        equipment.getName(),
                        equipment.getModel(),
                        equipment.getCost(),
                        equipment.getPurchase(),
                        equipment.getWarranty(),
                        equipment.getManufacture(),
                        equipment.getUser_id()
                );

                obList.add(tm);
            }

            tblequipment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





public void eqsaveAction(ActionEvent actionEvent) {String id = eqidtext.getText();
        String name = eqnametext.getText();
        String model = eqmodltext.getText();
        double cost = Double.parseDouble(eqcosttext.getText());
        String purchaseDate = eqpurtext.getText();
        String warranty = eqwartext.getText();
        String manufacturer = eqmanutext.getText();
        String userId = equseridtext.getText();

        Equipment equipment = new Equipment(id, name, model, cost, purchaseDate, warranty, manufacturer, userId);

        try {
            boolean isSaved = EquipmentRepo.save(equipment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Equipment saved!").show();
                clearFields();
                loadAllEquipment();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save equipment!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving equipment: " + e.getMessage()).show();
        }

    }

    private void clearFields() {
        eqidtext.clear();
        eqnametext.clear();
        eqmodltext.clear();
        eqcosttext.clear();
        eqpurtext.clear();
        eqwartext.clear();
        eqmanutext.clear();
        equseridtext.clear();
    }

    public void equpdateAction(ActionEvent actionEvent) {String id = eqidtext.getText();
        String name = eqnametext.getText();
        String model = eqmodltext.getText();
        double cost = Double.parseDouble(eqcosttext.getText());
        String purchaseDate = eqpurtext.getText();
        String warranty = eqwartext.getText();
        String manufacturer = eqmanutext.getText();
        String userId = equseridtext.getText();

        Equipment equipment = new Equipment(id, name, model, cost, purchaseDate, warranty, manufacturer, userId);

        try {
            boolean isUpdated = EquipmentRepo.update(equipment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Equipment updated!").show();

                clearFields();
                loadAllEquipment();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update equipment!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating equipment: " + e.getMessage()).show();
        }

    }

    public void eqdeleteAction(ActionEvent actionEvent) { String id = eqidtext.getText();

        try {
            boolean isDeleted = EquipmentRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Equipment deleted!").show();

                clearFields();
                loadAllEquipment();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error occurred while deleting equipment: " + e.getMessage()).show();
        }

    }

    public void eqorAction(ActionEvent actionEvent) {String id = eqidtext.getText();

        try {
            Equipment equipment = EquipmentRepo.searchById(id);
            if (equipment != null) {
                eqidtext.setText(equipment.getEq_id());
                eqnametext.setText(equipment.getName());
                eqmodltext.setText(equipment.getModel());
                eqcosttext.setText(String.valueOf(equipment.getCost()));
                eqpurtext.setText(equipment.getPurchase());
                eqwartext.setText(equipment.getWarranty());
                eqmanutext.setText(equipment.getManufacture());
                equseridtext.setText(equipment.getUser_id());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Equipment not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void eqclAction(ActionEvent actionEvent) {
clearFields();
    }
}
