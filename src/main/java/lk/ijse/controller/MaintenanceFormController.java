package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.tm.AddsTm;
import lk.ijse.model.tm.CartTm;
import lk.ijse.repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class MaintenanceFormController {
    public TableColumn<String, String> coleqid;
    public TableColumn<String, String> colspid;
    public TableColumn<String, String> coldesmain;
    public TableColumn<String, Double> colcostaction;
    public TableColumn<String, String> colmaintaintype;
    public TableColumn<String, JFXButton> colac;

    public TableView tblmaintenance;



    public ComboBox comspareid;

    public TextField maintypetext;


    @FXML
    private ComboBox<String> comempid;

    @FXML
    private ComboBox<String> comeqid;

    @FXML
    private TextField costtext;

    @FXML
    private TextField descriptiontext;

    @FXML
    private Label lbleqname;

    @FXML
    private Label lblmaidate;

    @FXML
    private Label lblmainid;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();







    @FXML
    void addOnAction(ActionEvent event) {
        // Retrieve values from UI components
        String equipmentid = comeqid.getValue();
        String types = maintypetext.getText();
        String description = descriptiontext.getText();
        double cost = 0.0; // Default value in case of parsing error

        // Parse cost from costtext TextField
        try {
            cost = Double.parseDouble(costtext.getText());
        } catch (NumberFormatException e) {
            // Handle parsing error
            e.printStackTrace();
            // You might want to show an error message to the user here
            return; // Exit the method if parsing fails
        }

        // Create a new AddsTm object with the retrieved values
        AddsTm tms = new AddsTm();
        tms.setEq_id(equipmentid);
        tms.setType(types);
        tms.setDescription(description);
        tms.setCost(cost);

        // Add the AddsTm object to the observable list
        obList.add(tms);

        // Refresh the TableView
        tblmaintenance.setItems(obList);
    }



    public void initialize() {
        setDate();
        getEmployeeid();
        getEquipmentid();
        //getsparepartid();
        setCellValueFactory();

        try {
            String currentmainId = MaintenanceRepo.getCurrentId();
            String nextOrderId = generateNextOrderId(currentmainId);
           lblmainid.setText(nextOrderId);
        } catch (SQLException e) {

            // Handle SQLException
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
       coleqid.setCellValueFactory(new PropertyValueFactory<>("eq_id"));
      colmaintaintype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldesmain.setCellValueFactory(new PropertyValueFactory<>("description"));
        colcostaction.setCellValueFactory(new PropertyValueFactory<>("cost"));

       // colac.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }



    private void getEquipmentid() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> equiList = EquipmentRepo.getIds();

            for (String id : equiList) {
                obList.add(id);
            }

           comeqid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeid() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> mainList = EmployeeRepo.getIds();

            for (String id : mainList) {
                obList.add(id);
            }

            comempid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNextOrderId(String currentMainId) {
        if (currentMainId != null && currentMainId.matches("^MM\\d{3}$")) {
            try {
                int mainId = Integer.parseInt(currentMainId.substring(2)) + 1;
                return "MM" + String.format("%03d", mainId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "MM001"; //
    }


    private void setDate() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    lblmaidate.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void removeonAction(ActionEvent actionEvent) {

    }
}
