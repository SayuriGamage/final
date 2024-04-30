package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Spareparts;
import lk.ijse.model.tm.SparepartsTm;
import lk.ijse.repository.MaintenanceRepo;
import lk.ijse.repository.SparepartsRepo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SparepartsFormController {
    public TextField sptext;
    public TextField spnametext;
    public TextField spmanutext;
    public TextField sppritext;
    public TextField spqtytext;
    public TextField sppurtext;
    public TextField spmaintext;
    public TableView<SparepartsTm> tblspareparts;
    public TableColumn<SparepartsTm, String> colids;
    public TableColumn<SparepartsTm, String> colspname;
    public TableColumn<SparepartsTm, String> colmanusp;
    public TableColumn<SparepartsTm, Double> colunisp;
    public TableColumn<SparepartsTm, Integer> colqtysp;
    public TableColumn<SparepartsTm, String> colpushsp;
    public TableColumn<SparepartsTm, String> colmainsp;
    public ComboBox comspid;
    public Label lblspdatetime;


    public void initialize() {
        loadAllSpareparts();
        setCellValueFactory();

        getmaintenanceid();
        setDatetime();
        tblspareparts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                SparepartsTm selectedSparepart = newSelection;
                sptext.setText(selectedSparepart.getSp_id());
                spnametext.setText(selectedSparepart.getName());
                spmanutext.setText(selectedSparepart.getManufacture());
                sppritext.setText(String.valueOf(selectedSparepart.getCost()));
                spqtytext.setText(String.valueOf(selectedSparepart.getQty()));
                sppurtext.setText(selectedSparepart.getPurchase());
                comspid.setValue(selectedSparepart.getMm_id());
            }
        });
    }

    private void setDatetime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    lblspdatetime.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void getmaintenanceid() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> spList = MaintenanceRepo.getIds();

            for (String id : spList) {
                obList.add(id);
            }

           comspid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colids.setCellValueFactory(new PropertyValueFactory<>("sp_id"));
        colspname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmanusp.setCellValueFactory(new PropertyValueFactory<>("manufacture"));
        colunisp.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colqtysp.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colpushsp.setCellValueFactory(new PropertyValueFactory<>("purchase"));
        colmainsp.setCellValueFactory(new PropertyValueFactory<>("mm_id"));
    }

    private void loadAllSpareparts() {
        ObservableList<SparepartsTm> obList = FXCollections.observableArrayList();

        try {
            List<Spareparts> sparepartsList = SparepartsRepo.getAll();

            for (Spareparts spareparts : sparepartsList) {
                SparepartsTm tm = new SparepartsTm(
                        spareparts.getSp_id(),
                        spareparts.getName(),
                        spareparts.getManufacture(),
                        spareparts.getCost(),
                        spareparts.getQty(),
                        spareparts.getPurchase(),
                        spareparts.getMm_id()
                );

                obList.add(tm);
            }

            tblspareparts.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void serchspidonAction(ActionEvent actionEvent) {
        String id = sptext.getText();

        try {
            Spareparts spareparts = SparepartsRepo.getById(id);
            if (spareparts != null) {
                spnametext.setText(spareparts.getName());
                spmanutext.setText(spareparts.getManufacture());
                sppritext.setText(String.valueOf(spareparts.getCost()));
                spqtytext.setText(String.valueOf(spareparts.getQty()));
                sppurtext.setText(spareparts.getPurchase());
                comspid.getSelectionModel().select(spareparts.getMm_id());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Spare part not found!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void savespAction(ActionEvent actionEvent) {
        String id = sptext.getText();
        String name = spnametext.getText();
        String manu = spmanutext.getText();
        double price = Double.parseDouble(sppritext.getText());
        int qty = Integer.parseInt(spqtytext.getText());
        String purch = sppurtext.getText();
        String main = comspid.getValue().toString();

        Spareparts spareparts = new Spareparts(id, name, manu, price, qty, purch, main);

        try {
            boolean isSaved = SparepartsRepo.save(spareparts);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "spareparts saved!").show();
                clearFields();
                loadAllSpareparts();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        sptext.setText("");
        spnametext.setText("");
        spmanutext.setText("");
        sppritext.setText("");
        spqtytext.setText("");
        sppurtext.setText("");
        comspid.getSelectionModel().clearSelection();
    }

    public void upspAction(ActionEvent actionEvent) {
        String id = sptext.getText();
        String name = spnametext.getText();
        String manu = spmanutext.getText();
        double price = Double.parseDouble(sppritext.getText());
        int qty = Integer.parseInt(spqtytext.getText());
        String purch = sppurtext.getText();
        String main = comspid.getValue().toString();

        Spareparts spareparts = new Spareparts(id, name, manu, price, qty, purch, main);

        try {
            boolean isUpdated = SparepartsRepo.update(spareparts);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Spare part updated!").show();
                clearFields();
                loadAllSpareparts();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delspAction(ActionEvent actionEvent) {
        String id = sptext.getText();

        try {
            boolean isDeleted = SparepartsRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Spare part deleted!").show();
                clearFields();
                loadAllSpareparts();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clespAction(ActionEvent actionEvent) {
        clearFields();
    }
}
