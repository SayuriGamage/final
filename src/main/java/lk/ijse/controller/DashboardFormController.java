package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane dashboardpane;
    public AnchorPane rootNode;

    public void initialize(){
        try {
            loadDashboardsec();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDashboardsec() throws IOException {
        AnchorPane dashi= FXMLLoader.load(this.getClass().getResource("/view/dashboardsec_form.fxml"));
        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(dashi);

    }


    public void mainAction(ActionEvent actionEvent) {
    }

    public void empAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/employee_form.fxml"));

        AnchorPane form =loader.load();

        dashboardpane.getChildren().clear();
        dashboardpane.getChildren().add(form);
    }

    public void spareAction(ActionEvent actionEvent) {
    }

    public void supAction(ActionEvent actionEvent) {
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

    public void orOnAction(ActionEvent actionEvent) {
    }

    public void payonAction(ActionEvent actionEvent) {
    }

    public void equipAction(ActionEvent actionEvent) {
    }
}
