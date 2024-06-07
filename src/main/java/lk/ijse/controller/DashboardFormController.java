package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.email.GmailSender;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController  {

    public AnchorPane dashboardpane;
    public AnchorPane rootNode;
    public Label lblusernamedashboard;
    private String loggedInUsername;

    static String password;




    public void initialize(){

        try {
            loadDashboardsec();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   public void setUsername(String username) {
        loggedInUsername = username;
        lblusernamedashboard.setText(loggedInUsername);
    }

    private void loadDashboardsec() throws IOException {
        AnchorPane dashi= FXMLLoader.load(this.getClass().getResource("/view/dashboardsec_form.fxml"));
        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(dashi);

    }


    public void mainAction(ActionEvent actionEvent) throws IOException {
        AnchorPane form = FXMLLoader.load(getClass().getResource("/view/maintenance_form.fxml"));

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }

    public void empAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/employee_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);

    }

    public void spareAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/spareparts_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }





    public void condeminAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/condemned_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }


    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/dashboardsec_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }

    public void orOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/orders_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }

    public void payonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/payment_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }

    public void equipAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/equipment_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }

    public void suponAction(ActionEvent actionEvent) throws IOException {

        AnchorPane supPane = FXMLLoader.load(getClass().getResource("/view/supplier_from.fxml"));


        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(supPane);
    }

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }


}
