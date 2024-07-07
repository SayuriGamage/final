/*package lk.ijse.controller;

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
import lk.ijse.model.*;
import lk.ijse.model.tm.AddsTm;
import lk.ijse.model.tm.CartTm;
import lk.ijse.repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaintenanceFormController {
    public TableColumn<String, String> coleqid;
    public TableColumn<String, String> colspid;
    public TableColumn<String, String> coldesmain;
    public TableColumn<String, Double> colcostaction;
    public TableColumn<String, String> colmaintaintype;
    public TableColumn<String, JFXButton> colrem;

    public TableView tblmaintenance;




    public TextField maintypetext;
   // public TableColumn colrem;



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
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                CartTm selectedCartItem = (CartTm) tblmaintenance.getSelectionModel().getSelectedItem();

                if (selectedCartItem != null) {

                    obList.remove(selectedCartItem);

                    tblmaintenance.refresh();

                } else {

                    new Alert(Alert.AlertType.WARNING, "Please select an item to remove.").show();
                }
            }
        });

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
        colrem.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

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

    public void removeonAction(ActionEvent actionEvent) throws SQLException {
        String maintenanceId = lblmainid.getText();
        String date = String.valueOf(LocalDate.now());
        String description = descriptiontext.getText();
        double cost = Double.parseDouble(costtext.getText());
        String employeeId = comempid.getValue();
        String equipmentId=comeqid.getValue();
        String type = maintypetext.getText();
        var maintenance = new Maintenance(maintenanceId,date,description,cost,employeeId);
        List<equipmentDetails> osList = new ArrayList<>();

        for (int i = 0; i < tblmaintenance.getItems().size(); i++) {
            AddsTm am = (AddsTm) obList.get(i);
            //   System.out.println(tm.getSp_id());
            equipmentDetails od = new equipmentDetails(

                    type,
                    maintenanceId,
                    am.getEq_id()

            );

            osList.add(od);
        }

        placeMaintenance po = new placeMaintenance(maintenance,osList);
        boolean isPlaced = PlaceMaintenanceRepo.placeMaintenance(po);
        if(isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "maintenance details Placed!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, " Placed Unsuccessfully!").show();
        }

    }
}*/
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
import lk.ijse.DTO.placeMaintenanceDTO;
import lk.ijse.bo.MaintenanceBO;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.bo.impl.BOTypes;
import lk.ijse.bo.impl.MaintenanaceBOImpl;
import lk.ijse.dao.*;
import lk.ijse.dao.Impl.*;
import lk.ijse.entity.*;
import lk.ijse.entity.tm.AddsTm;
import lk.ijse.entity.tm.CartTm;
import lk.ijse.util.Regex;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceFormController {
    public TextField maintext;

    @FXML
    private TableColumn<String, String> coleqid;

    @FXML
    private TableColumn<String, String> coldesmain;
    @FXML
    private TableColumn<String, Double> colcostaction;
    @FXML
    private TableColumn<String, String> colmaintaintype;


    @FXML
    private TableView tblmaintenance;

    @FXML
    private ComboBox<String> comempid;
    @FXML
    private ComboBox<String> comeqid;
    @FXML
    private TextField costtext;
    @FXML
    private TextField descriptiontext;
    @FXML
    private TextField maintypetext;

    @FXML
    private Label lblmaidate;
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
    MaintenanceBO maintenanceBO= (MaintenanceBO) BOFactory.getBoFactory().getBO(BOTypes.Maintenancebo);


    public void initialize() throws SQLException {
        setDate();
        getEmployeeid();
        getEquipmentid();
        setCellValueFactory();
        tblmaintenance.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                AddsTm selectedMaintenance = (AddsTm) newSelection;
                maintypetext.setText(selectedMaintenance.getType());
                descriptiontext.setText(selectedMaintenance.getDescription());
                costtext.setText(String.valueOf(selectedMaintenance.getCost()));
           comeqid.setValue(selectedMaintenance.getEq_id());

                String maintenanceId = null;
                String empId=null;
                try {
                    maintenanceId = maintenanceBO.getMaintenanceId(selectedMaintenance.getDescription(),selectedMaintenance.getCost());
                    empId=maintenanceBO.getMaintenanceempId(selectedMaintenance.getDescription(),selectedMaintenance.getCost());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                maintext.setText(maintenanceId);
                comempid.setValue(empId);


            }
        });
            loadMaintenanceData();
        String   currentmainId = maintenanceBO.getCurrentMaintenanceId();
        String nextmainId = generateNextmId(currentmainId);
        maintext.setText(nextmainId);
    }

    private String generateNextmId(String currentmainId) {
        if (currentmainId != null && currentmainId.matches("^MAI\\d+$")) {

            String numericPart = currentmainId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "MAI" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "MAI001";
    }


    private void loadMaintenanceData() throws SQLException {
        List<AddsTm> adds = new ArrayList<>();
        AddsDAO addsDAO=new AddsDAOImpl();
        adds = addsDAO.getAllAddsTm();
        tblmaintenance.getItems().clear();
        for (AddsTm addsTm : adds) {
            tblmaintenance.getItems().add(addsTm);
        }
    }


    private void setCellValueFactory() {
        coleqid.setCellValueFactory(new PropertyValueFactory<>("eq_id"));
        colmaintaintype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldesmain.setCellValueFactory(new PropertyValueFactory<>("description"));
        colcostaction.setCellValueFactory(new PropertyValueFactory<>("cost"));

    }


    private void getEquipmentid() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> equiList = maintenanceBO.getEquipmentIds();
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

            List<String> mainList = maintenanceBO.getIdEmployee();
            for (String id : mainList) {
                obList.add(id);
            }
            comempid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void removeonAction(ActionEvent actionEvent) throws SQLException {
        String maintenanceId = maintext.getText();
        String date = String.valueOf(LocalDate.now());
        String description = descriptiontext.getText();
        double cost = Double.parseDouble(costtext.getText());
        String employeeId = comempid.getValue();
        String equipmentId = comeqid.getValue();
        String type = maintypetext.getText();
        Maintenance maintenance = new Maintenance(maintenanceId, date, description, cost, employeeId);
        List<EquipmentDetails> osList = new ArrayList<>();
        for (int i = 0; i < tblmaintenance.getItems().size(); i++) {
            AddsTm am = (AddsTm) obList.get(i);
            EquipmentDetails od = new EquipmentDetails(
                    type,
                    maintenanceId,
                    am.getEq_id()
            );
            osList.add(od);
        }
        placeMaintenanceDTO po = new placeMaintenanceDTO(maintenance, osList);
        boolean isPlaced = maintenanceBO.placeMaintenances(po);

        if (isPlaced) {

                new Alert(Alert.AlertType.CONFIRMATION, "Maintenance  Placed!").show();
                clearTextFields();
                obList.clear();
                tblmaintenance.refresh();
                loadMaintenanceData();


        } else {
            new Alert(Alert.AlertType.WARNING, "Placed Unsuccessfully!").show();
        }
    }


    private void clearTextFields() {
        comeqid.getSelectionModel().clearSelection();
        comempid.getSelectionModel().clearSelection();
        maintypetext.setText("");
        costtext.setText("");
        descriptiontext.setText("");
        maintext.setText("");



    }

    public void deleteAction(ActionEvent actionEvent) {

        CartTm selectedItem = (CartTm) tblmaintenance.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            obList.remove(selectedItem);
            tblmaintenance.refresh();
        } else {

            new Alert(Alert.AlertType.WARNING, "Please select an item to delete.").show();
        }
    }

    public void costAction(KeyEvent keyEvent) {
       Regex.setTextColor(lk.ijse.util.TextField.COST,costtext);
    }

    public void finaladddAction(ActionEvent actionEvent) throws SQLException {
        String equipmentid = comeqid.getValue();
        String types = maintypetext.getText();
        String description = descriptiontext.getText();
        double cost = 0.0;



        try {
            cost = Double.parseDouble(costtext.getText());
        } catch (NumberFormatException e) {

            e.printStackTrace();

            return;
        }


        AddsTm tms = new AddsTm();
        tms.setEq_id(equipmentid);
        tms.setType(types);
        tms.setDescription(description);
        tms.setCost(cost);

        obList.add(tms);

        tblmaintenance.setItems(obList);
    }



   
    public boolean valid(){
        if (!Regex.setTextColor(lk.ijse.util.TextField.TEXT,maintypetext)) return false;
        if(!Regex.setTextColor(lk.ijse.util.TextField.COST,costtext)) return false;
        if(!Regex.setTextColor(lk.ijse.util.TextField.TEXT,descriptiontext)) return false;
        return  true;
    }

    public void typeaction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.TEXT,maintypetext);
    }

    public void descripaction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.TEXT,descriptiontext);
    }


}
