package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Condemned;
import lk.ijse.model.tm.CondemnedTm;
import lk.ijse.repository.CondemnedRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CondemnedFormController {

    public TableView<CondemnedTm> tblcondemned;
    public TextField reasontext;
    public TextField mainftext;
    public TextField datetext;
    public TableColumn<CondemnedTm, String> colcon;
    public TableColumn<CondemnedTm, String> colreason;
    public TableColumn<CondemnedTm, String> coldate;
    public TableColumn<CondemnedTm, String> colmaintenace;

    public TextField context;

    public void initialize() {
        setCellValueFactory();
        loadAllCondemned();
    }

    private void setCellValueFactory() {
        colcon.setCellValueFactory(new PropertyValueFactory<>("c_id"));
        colreason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmaintenace.setCellValueFactory(new PropertyValueFactory<>("mm_id"));
    }

    private void loadAllCondemned() {
        ObservableList<CondemnedTm> obList = FXCollections.observableArrayList();

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
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = context.getText();
        String details = reasontext.getText();
        String date = datetext.getText(); // Parse the date string to LocalDate
        String mid = mainftext.getText();

        Condemned condemned = new Condemned(id, details, date, mid);

        boolean isSaved = CondemnedRepo.save(condemned);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "condemn saved!").show();
            clearFields();
        }
    }

    private void clearFields() {
        context.setText("");
        reasontext.setText("");
        datetext.setText("");
        mainftext.setText("");
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = context.getText();
        String reason = reasontext.getText();
        String date =datetext.getText(); // Parse the date string to LocalDate
        String mid = mainftext.getText();

        Condemned condemned = new Condemned(id, reason, date, mid);

        boolean isUpdated = CondemnedRepo.update(condemned);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "condemn updated!").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = context.getText();

        boolean isDeleted = CondemnedRepo.delete(id);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "condemned deleted!").show();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void conOnActionsearch(ActionEvent actionEvent) {
        String id = context.getText();

        Condemned condemned = CondemnedRepo.searchById(id);
        if (condemned != null) {
            reasontext.setText(condemned.getDetails());
            datetext.setText(condemned.getDate().toString());
            mainftext.setText(condemned.getMm_id());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "condemned not found!").show();
        }
    }
}
