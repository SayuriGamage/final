package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DashboardsecFormController {



    public AnchorPane rootNode;
    public TextField dashboardsearchtext;
    public TextField dateatext;
    public Label lblitemCount;
    public Label desdate;
    public Label lblempcount;
    public Label lblequicount;
    public BarChart barChart;
    private  int empCount;
    private int itemCount;
    @FXML
    private LineChart<?, ?> linechart;
    private int equipmentCount;

    public void initialize() throws SQLException {
        setDate();
        itemCount=getItemCount();
        setItemCount(itemCount);
        empCount=getEmployeeCount();
        setEmpCount(empCount);
        equipmentCount=getEquipmentCount();
        setEquipmentCount(equipmentCount);
        lineChart();
    }

    private void setEquipmentCount(int equipmentCount) {
        lblequicount.setText(String.valueOf(equipmentCount));
    }

    private int getEquipmentCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS equipment_count FROM equipment;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("equipment_count");
        }
        return 0;
    }

    private void setEmpCount(int empCount) {
        lblempcount.setText(String.valueOf(empCount));
    }


    private int getEmployeeCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS employee_count FROM employee;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("employee_count");
        }
        return 0;
    }

    private void setDate() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);
                    desdate.setText(formattedDateTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setItemCount(int itemCount) {
        lblitemCount.setText(String.valueOf(itemCount));
    }

    private int getItemCount() throws SQLException {
        String sql = "SELECT SUM(qty) AS total_qty FROM spareparts;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("total_qty");
        }
        return 0;
    }


    public void equipdetailAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/test3.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> datas = new HashMap<>();
        datas.put("id",dashboardsearchtext.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, datas, DbConnection.getInstance
                        ().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
        dashboardsearchtext.setText("");
    }

    public void todayAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/new.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("date",dateatext.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
        dateatext.setText("");
    }

    public void lineChart(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("maintenance cost");

        PreparedStatement stm = null;
        try {
            stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT\n" +
                    "    DATE_FORMAT(MIN(o.order_date), '%Y-%m-%d') AS WeekStartDate,\n" +
                    "    DATE_FORMAT(MAX(o.order_date), '%Y-%m-%d') AS WeekEndDate,\n" +
                    "    COUNT(*) AS WeeklyOrders,\n" +
                    "    SUM(p.amount) AS TotalAmount\n" +
                    "FROM\n" +
                    "    orders o\n" +
                    "JOIN\n" +
                    "    payment p ON o.or_id = p.or_id\n" +
                    "WHERE\n" +
                    "    o.order_date BETWEEN (SELECT MIN(order_date) FROM orders) AND (SELECT MAX(order_date) FROM orders)\n" +
                    "GROUP BY\n" +
                    "    YEARWEEK(o.order_date, 1)\n" +
                    "ORDER BY\n" +
                    "    WeekStartDate;\n ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rst = null;
        try {
            rst = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                if (!rst.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String date = null;
            try {
                date = rst.getString(2);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int count = 0;
            try {
                count = rst.getInt(4);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            series1.getData().add(new XYChart.Data<>(date, count));
        }
        barChart.getData().addAll(series1);
    }

}
