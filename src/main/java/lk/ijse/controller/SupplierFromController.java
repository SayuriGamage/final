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
import lk.ijse.DTO.SupplierDTO;
import lk.ijse.bo.SupplierBO;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.bo.impl.BOTypes;
import lk.ijse.entity.Supplier;
import lk.ijse.entity.tm.SupplierTm;
import lk.ijse.util.Regex;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SupplierFromController {

    public Label lblsupdatetim;
    public TableColumn coltelsup;
    public TextField suptel;


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


     SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOTypes.Supplierbo);

    @FXML
    void clearAction(ActionEvent event) throws SQLException {
        clearFields();
        String   currenteqId =supplierBO.getCurrentSupplierId();
        String nextempId = generateNextspId(currenteqId);
        supidtext.setText(nextempId);

    }

    @FXML
    void deleteAction(ActionEvent event) {
        String id = supidtext.getText();

        try {
            boolean isDeleted = supplierBO.deleteSupplier(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                clearFields();
                loadAllSupplier();
                setCellValueFactory();
                String   currenteqId = supplierBO.getCurrentSupplierId();
                String nextempId = generateNextspId(currenteqId);
                supidtext.setText(nextempId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveAction(ActionEvent event) {
        String id = supidtext.getText();
        String name = supnametext.getText();
String tels=suptel.getText();

      //  Supplier supplier = new Supplier(id, name,tels);

        try {

            boolean isSaved = supplierBO.saveSupplier(new SupplierDTO(id,name,tels));
            if (isSaved) {

                    new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                    clearFields();
                    loadAllSupplier();
                    setCellValueFactory();
                    String   currenteqId = supplierBO.getCurrentSupplierId();
                    String nextempId = generateNextspId(currenteqId);
                    supidtext.setText(nextempId);
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        supidtext.setText("");
        supnametext.setText("");
        suptel.setText("");
    }




    @FXML
    void updateAction(ActionEvent event) {
        String id = supidtext.getText();
        String name = supnametext.getText();
String tels=suptel.getText();

      //  Supplier supplier = new Supplier(id, name,tels);

        try {
            boolean isUpdated = supplierBO.updateSupplier(new SupplierDTO(id,name,tels));
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
                clearFields();
                loadAllSupplier();
                setCellValueFactory();
                String   currenteqId = supplierBO.getCurrentSupplierId();
                String nextempId = generateNextspId(currenteqId);
                supidtext.setText(nextempId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void initialize() throws SQLException {
        loadAllSupplier();
        setCellValueFactory();

        setdate();
        suppliertbl.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                SupplierTm selectedSupplier = newSelection;
                supidtext.setText(selectedSupplier.getSup_id());
                supnametext.setText(selectedSupplier.getName());
                suptel.setText(selectedSupplier.getTel());
            }
        });
        String   currenteqId = supplierBO.getCurrentSupplierId();
        String nextempId = generateNextspId(currenteqId);
        supidtext.setText(nextempId);
    }

    private String generateNextspId(String currenteqId) {
        if (currenteqId != null && currenteqId.matches("^SUP\\d+$")) {

            String numericPart = currenteqId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "SUP" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "SUP001";
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
coltelsup.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList =supplierBO.getAllSupplier();

            for (SupplierDTO supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSup_id(),
                        supplier.getName(),
supplier.getTel()
                );

                obList.add(tm);
            }

            suppliertbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public  boolean valid(){
        if(!Regex.setTextColor(lk.ijse.util.TextField.CONTACT,suptel)) return  false;
        if(!Regex.setTextColor(lk.ijse.util.TextField.NAME,supnametext)) return  false;
        return  true;
    }

    public void searchsuptel(ActionEvent actionEvent) throws SQLException {
      String tell=suptel.getText();
       SupplierDTO supplier= supplierBO .searchSupplier(tell);
      if (supplier!= null){
    supidtext.setText(supplier.getSup_id());
    supnametext.setText(supplier.getName());
    suptel.setText(supplier.getTel());
        }
    }

    public void suptelacction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.CONTACT,suptel);
    }

    public void nameaction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME,supnametext);
    }
}