package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.db.DbConnection;
import lk.ijse.model.*;
import lk.ijse.model.tm.CartTm;
import lk.ijse.repository.OrdersRepo;
import lk.ijse.repository.PlaceOrderRepo;
import lk.ijse.repository.SparepartsRepo;
import lk.ijse.repository.SupplierRepo;
import lk.ijse.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public Label lblbalance;
    public TextField lblcash;
    public TextField textqty;
    public TextField suppliertel;


    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();

        getSupplierTel();
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


private void getSupplierTel()  {
        ObservableList<String> obList=FXCollections.observableArrayList();
    List<String> telList= null;
    try {
        telList = SupplierRepo.getTells();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    for (String tel: telList){
            obList.add(tel);
        }
       // lblsupnam.setItems(obList);
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


    private Object calculateBalance() {
        double totalCost = Double.parseDouble(lbltotalfinal.getText());
        double cashPaid = Double.parseDouble(lblcash.getText());
        double balance = cashPaid - totalCost;
        lblbalance.setText(String.valueOf(balance));
        return balance;
    }


    private Object calculateNetTotal() {
        double netTotal = 0;
        for (CartTm tm : obList) {
            netTotal += tm.getTotal();
        }
        lbltotalfinal.setText(String.valueOf(netTotal));
        return netTotal;
    }

    public void comnameAction(ActionEvent actionEvent) {
        String id = comsupid.getValue();
        try {
            Supplier supplier = SupplierRepo.searchBytell(id);

            lblsupnam.setText(supplier.getSup_id());

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
        String supId = lblsupnam.getText();


        String date = String.valueOf(Date.valueOf(LocalDate.now()));

        var order = new Orders(orderId, date, supId);


        List<OrderDetail> odList = new ArrayList<>();

        for (int i = 0; i < tblorder.getItems().size(); i++) {
            CartTm tm = obList.get(i);

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
lblcash.requestFocus();

        } else {
            new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
        }
    }
    private void clearTextFields() {
     comsupid.getSelectionModel().clearSelection();
     comspid.getSelectionModel().clearSelection();
        textqty.setText("");
        lblorid.setText("");
        lblsupnam.setText("");
        lblsparename.setText("");
        lblunitprice.setText("");
        lbltotalfinal.setText("");
        lblcash.setText("");
        lblbalance.setText("");

    }

    public void printbillorAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/order.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> datat = new HashMap<>();
        String orderId = lblorid.getText();
        datat.put("ids",orderId);
        String netTotalString = String.valueOf(calculateNetTotal());
        datat.put("total", netTotalString);
        String cash=lblcash.getText();
        datat.put("cash",cash);
        String bala=String.valueOf(calculateBalance());
        datat.put("balance",bala);


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, datat, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

       obList.clear();
        tblorder.refresh();
        clearTextFields();
        String currentOrderId = OrdersRepo.getCurrentId();
        String nextOrderId = generateNextOrderId(currentOrderId);
        lblorid.setText(nextOrderId);
    }

    public void calculBalance(ActionEvent actionEvent) {
        calculateBalance();
    }

    public void addtonAction(ActionEvent actionEvent) {
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


    public void suptelAction(ActionEvent actionEvent) {
        String id = suppliertel.getText();
        try {
            Supplier supplier = SupplierRepo.searchBytell(id);

            lblsupnam.setText(supplier.getSup_id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void contactaction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.util.TextField.CONTACT,suppliertel);
    }
}


