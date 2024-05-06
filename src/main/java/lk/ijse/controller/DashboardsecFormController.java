package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DashboardsecFormController {



    public AnchorPane rootNode;
    public TextField dashboardsearchtext;
    public TextField dateatext;
    public Label lblitemCount;

    private int itemCount;


    public void initialize() throws SQLException {
        itemCount=getItemCount();
        setItemCount(itemCount);
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
    }

    public void todayAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/new.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("date",dateatext.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
