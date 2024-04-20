package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Spareparts;
import lk.ijse.model.tm.SparepartsTm;
import lk.ijse.repository.SparepartsRepo;

import java.sql.SQLException;
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

    public void initialize() {
        setCellValueFactory();
        loadAllSpareparts();
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
                spmaintext.setText(spareparts.getMm_id());
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
        String main = spmaintext.getText();

        Spareparts spareparts = new Spareparts(id, name, manu, price, qty, purch, main);

        try {
            boolean isSaved = SparepartsRepo.save(spareparts);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "spareparts saved!").show();
                clearFields();
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
        spmaintext.setText("");
    }

    public void upspAction(ActionEvent actionEvent) {
        String id = sptext.getText();
        String name = spnametext.getText();
        String manu = spmanutext.getText();
        double price = Double.parseDouble(sppritext.getText());
        int qty = Integer.parseInt(spqtytext.getText());
        String purch = sppurtext.getText();
        String main = spmaintext.getText();

        Spareparts spareparts = new Spareparts(id, name, manu, price, qty, purch, main);

        try {
            boolean isUpdated = SparepartsRepo.update(spareparts);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Spare part updated!").show();
                clearFields();
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clespAction(ActionEvent actionEvent) {
        clearFields();
    }
}
