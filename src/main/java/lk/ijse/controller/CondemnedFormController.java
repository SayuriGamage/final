package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Condemned;
import lk.ijse.model.tm.CondemnedTm;
import lk.ijse.repository.CondemnedRepo;
import lk.ijse.repository.MaintenanceRepo;
import lk.ijse.repository.OrdersRepo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CondemnedFormController {
    public TextField datetext;
    public TextField mainftext;
    public TextField reasontext;
    public TableColumn<CondemnedTm, String> colmaintenace;
    public TableColumn<CondemnedTm, String> coldate;
    public TableColumn<CondemnedTm, String> colreason;
    public TableColumn<CondemnedTm, String> colcon;
    public TableView<CondemnedTm> tblcondemned;
    public TextField context;
    public ComboBox comconid;
    public Label lblcondatetime;


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
            List<String> coList = MaintenanceRepo.getIds();

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
            List<Condemned> condemnedList = CondemnedRepo.getAll();

            for (Condemned condemned : condemnedList) {
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

        Condemned condemned = new Condemned(id, name, address, tel);

        try {
            boolean isSaved = CondemnedRepo.save(condemned);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "condemned saved!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
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

        Condemned condemned = new Condemned(id, name, address, tel);

        try {
            boolean isUpdated = CondemnedRepo.update(condemned);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "condemd updated!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
        String id = context.getText();

        try {
            boolean isDeleted = CondemnedRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "condemn deleted!").show();
                clearFields();
                loadAllCustomers();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void conOnActionsearch(ActionEvent actionEvent) throws SQLException {
        String id = context.getText();

        Condemned condemned = CondemnedRepo.searchById(id);
        if (condemned != null) {
            context.setText(condemned.getC_id());
            reasontext.setText(condemned.getDetails());
            datetext.setText(condemned.getDate());
            comconid.getSelectionModel().select(condemned.getMm_id());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "cundemd not found!").show();
        }
    }
}
