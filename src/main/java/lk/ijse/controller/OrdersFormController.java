package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.model.*;
import lk.ijse.model.tm.CartTm;
import lk.ijse.repository.OrdersRepo;
import lk.ijse.repository.PlaceOrderRepo;
import lk.ijse.repository.SparepartsRepo;
import lk.ijse.repository.SupplierRepo;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class  OrdersFormController {
    public Label lblorid;
    public Label lblordate;
    public TableView<CartTm> tblorder;
    public TableColumn<CartTm, String> colorid;
    public TableColumn<CartTm, String> colorname;
    public TableColumn<CartTm, Double> colunitprice;
    public TableColumn<CartTm, Integer> colorqty;
    public TableColumn<CartTm, Double> coltotal;
    public TableColumn<CartTm, JFXButton> colaction;
    public Label lbltotalfinal;
    public ComboBox<String> comsupid;
    public ComboBox<String> comspid;
    public Label lblsupnam;
    public Label lblsparename;
    public Label lblunitprice;
    private AnchorPane dashboardpane;
    public TextField textqty;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        getSuppliersid();
        getSparepartsCode();
        setCellValueFactory();
        try {
            String currentOrderId = OrdersRepo.getCurrentId();
            String nextOrderId = generateNextOrderId(currentOrderId);
            lblorid.setText(nextOrderId);
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        }
    }

    private String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null && currentOrderId.matches("^ORD\\d+$")) {

            String numericPart = currentOrderId.substring(3);
            try {

                int orderId = Integer.parseInt(numericPart) + 1;

                return "ORD" + String.format("%03d", orderId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "ORD001";
    }


    private void setCellValueFactory() {
        colorid.setCellValueFactory(new PropertyValueFactory<>("sp_id"));
        colorname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colorqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colaction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getSparepartsCode() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = SparepartsRepo.getCodes();

            for (String code : codeList) {
                obList.add(code);
            }
            comspid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getSuppliersid() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SupplierRepo.getIds();

            for (String id : idList) {
                obList.add(id);
            }

            comsupid.setItems(obList);

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
                    lblordate.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void addcartAction(ActionEvent actionEvent) {
        String orderId = lblorid.getText(); // Get the current order ID
        String code = comspid.getValue();
        String description = lblsparename.getText();
        int qty = Integer.parseInt(textqty.getText());
        double unitPrice = Double.parseDouble(lblunitprice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {

                CartTm selectedCartItem = tblorder.getSelectionModel().getSelectedItem();

                if (selectedCartItem != null) {

                    obList.remove(selectedCartItem);

                    tblorder.refresh();
                    calculateNetTotal();
                } else {

                    new Alert(Alert.AlertType.WARNING, "Please select an item to remove.").show();
                }
            }
        });


        CartTm tm = new CartTm(code, description, qty, unitPrice, total, btnRemove);
        obList.add(tm);

        tblorder.setItems(obList);
        calculateNetTotal();
        textqty.setText("");
    }


    private void calculateNetTotal() {
        double netTotal = 0;
        for (CartTm tm : obList) {
            netTotal += tm.getTotal();
        }
        lbltotalfinal.setText(String.valueOf(netTotal));
    }

    public void comnameAction(ActionEvent actionEvent) {
        String id = comsupid.getValue();
        try {
            Supplier supplier = SupplierRepo.searchById(id);

            lblsupnam.setText(supplier.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comspareAction(ActionEvent actionEvent) {
        String code = comspid.getValue();

        try {
            Spareparts spareparts = SparepartsRepo.searchById(code);
            if (spareparts != null) {
                lblsparename.setText(spareparts.getName());
                lblunitprice.setText(String.valueOf(spareparts.getCost()));
            }

            textqty.requestFocus();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void placeorderAction(ActionEvent actionEvent) throws SQLException {
        String orderId = lblorid.getText();
        String supId = comsupid.getValue();


        String date = String.valueOf(Date.valueOf(LocalDate.now()));

        var order = new Orders(orderId, date, supId);

        List<OrderDetail> odList = new ArrayList<>();

        for (int i = 0; i < tblorder.getItems().size(); i++) {
            CartTm tm = obList.get(i);
            //   System.out.println(tm.getSp_id());
            OrderDetail od = new OrderDetail(

                    orderId,
                    tm.getSp_id(),
                    tm.getQty(),
                    tm.getCost()
            );

            odList.add(od);
        }

        PlaceOrder po = new PlaceOrder(order, odList);
        boolean isPlaced = PlaceOrderRepo.placeOrder(po);
        if (isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
        }
    }
}


