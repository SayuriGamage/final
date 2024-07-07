package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import lk.ijse.DTO.CondemnedDTO;
import lk.ijse.bo.CondemnedBO;
import lk.ijse.bo.MaintenanceBO;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.bo.impl.BOTypes;
import lk.ijse.bo.impl.CondemnedBOImpl;
import lk.ijse.dao.CondemnedDAO;
import lk.ijse.entity.Condemned;
import lk.ijse.entity.tm.CondemnedTm;
import lk.ijse.dao.Impl.CondemnedDAOImpl;
import lk.ijse.dao.Impl.MaintenanceDAOImpl;
import lk.ijse.util.Regex;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CondemnedFormController {
    public TextField datetext;

    public TextField reasontext;
    public TableColumn<CondemnedTm, String> colmaintenace;
    public TableColumn<CondemnedTm, String> coldate;
    public TableColumn<CondemnedTm, String> colreason;
    public TableColumn<CondemnedTm, String> colcon;
    public TableView<CondemnedTm> tblcondemned;
    public TextField context;
    public ComboBox comconid;
    public Label lblcondatetime;

      CondemnedBO condemnedBO= (CondemnedBO) BOFactory.getBoFactory().getBO(BOTypes.Condemnedbo);
    MaintenanceBO maintenanceBO= (MaintenanceBO) BOFactory.getBoFactory().getBO(BOTypes.Maintenancebo);

    public void initialize() {
        loadAllCustomers();
        setCellValueFactory();

        getmaintenanceid();
        setDatetime();
        tblcondemned.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                CondemnedTm selectedCondemned = newSelection;
                context.setText(selectedCondemned.getC_id());
                reasontext.setText(selectedCondemned.getDetails());
                datetext.setText(selectedCondemned.getDate());
                comconid.getSelectionModel().select(selectedCondemned.getMm_id());
            }
        });


        String   currentconId = null;
        try {
            currentconId = condemnedBO.getCurrentCondemnId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String nextempId = generateNextconId(currentconId);
        context.setText(nextempId);
    }

    private String generateNextconId(String currentconId) {
        if (currentconId != null && currentconId.matches("^CON\\d+$")) {

            String numericPart = currentconId.substring(3);
            try {

                int CONId = Integer.parseInt(numericPart) + 1;

                return "CON" + String.format("%03d", CONId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "CON001";
    }

    private void setDatetime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                 lblcondatetime.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void getmaintenanceid() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {

            List<String> coList = maintenanceBO.getMaintenanceIds();

            for (String id : coList) {
                obList.add(id);
            }

            comconid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCustomers() {
        ObservableList<CondemnedTm> obList = FXCollections.observableArrayList();

        try {

            List<CondemnedDTO> condemnedList = condemnedBO.getAllCondemn();

            for (CondemnedDTO condemned : condemnedList) {
                CondemnedTm tm = new CondemnedTm(
                        condemned.getC_id(),
                        condemned.getDetails(),
                        condemned.getDate(),
                        condemned.getMm_id()
                );

                obList.add(tm);
            }

            tblcondemned.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colcon.setCellValueFactory(new PropertyValueFactory<>("c_id"));
        colreason.setCellValueFactory(new PropertyValueFactory<>("details"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmaintenace.setCellValueFactory(new PropertyValueFactory<>("mm_id"));
    }

    public void saveAction(ActionEvent actionEvent) throws RuntimeException {
        String id = context.getText();
        String name = reasontext.getText();
        String address = datetext.getText();
        String tel =comconid.getValue().toString();

       // Condemned condemned = new Condemned(id, name, address, tel);

        try {

                boolean isSaved = condemnedBO.saveCondemn(new CondemnedDTO(id,name,address,tel));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "condemned saved!").show();
                    clearFields();
                    loadAllCustomers();
                    setCellValueFactory();

                    String currentconId = condemnedBO.getCurrentCondemnId();
                    String nextempId = generateNextconId(currentconId);
                    context.setText(nextempId);
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        context.setText("");
        reasontext.setText("");
        datetext.setText("");
        comconid.getSelectionModel().clearSelection();

    }

    public void updateAction(ActionEvent actionEvent) {
        String id = context.getText();
        String name = reasontext.getText();
        String address = datetext.getText();
        String tel = comconid.getValue().toString();

       // Condemned condemned = new Condemned(id, name, address, tel);

        try {
            boolean isUpdated = condemnedBO.updateCondemn(new CondemnedDTO(id,name,address,tel));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "condemd updated!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
                String   currentconId = condemnedBO.getCurrentCondemnId();
                String nextempId = generateNextconId(currentconId);
                context.setText(nextempId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
        String id = context.getText();

        try {
            boolean isDeleted = condemnedBO.deleteCondemn(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "condemn deleted!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
                String   currentconId = condemnedBO.getCurrentCondemnId();
                String nextempId = generateNextconId(currentconId);
                context.setText(nextempId);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearAction(ActionEvent actionEvent) throws SQLException {
        clearFields();
        String   currentconId = condemnedBO.getCurrentCondemnId();
        String nextempId = generateNextconId(currentconId);
        context.setText(nextempId);
    }

    public void conOnActionsearch(ActionEvent actionEvent) throws SQLException {
        String id = context.getText();

        CondemnedDTO condemned = condemnedBO.searchCondemn(id);
        if (condemned != null) {
            context.setText(condemned.getC_id());
            reasontext.setText(condemned.getDetails());
            datetext.setText(condemned.getDate());
            comconid.getSelectionModel().select(condemned.getMm_id());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "cundemd not found!").show();
        }
    }

    public void condateAction(KeyEvent keyEvent) {

    //   Regex.setTextColor(lk.ijse.util.TextField.ID,context);
    }

    public void conidAction(KeyEvent keyEvent) {

        Regex.setTextColor(lk.ijse.util.TextField.DATE,datetext);
    }
    public boolean valid(){
        if (!Regex.setTextColor(lk.ijse.util.TextField.TEXT,reasontext)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.DATE,datetext)) return false;
        return  true;
    }

    public void reasonAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.TEXT,reasontext);
    }
}
