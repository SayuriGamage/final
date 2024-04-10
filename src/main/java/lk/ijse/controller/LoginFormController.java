package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public TextField usernametext;
    public TextField passwordtext;
    public AnchorPane rootNode;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        String userId = usernametext.getText();
        String pw = passwordtext.getText();
        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "select user_id,password from user where user_id=?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String dbpw = resultSet.getString("password");
            if (pw.equals(dbpw)) {

                navigateDashboard();

            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry, the password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Sorry, user ID not found!").show();
        }
    }

    private void navigateDashboard() throws IOException {

        Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(dashboardRoot);
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }

    public void signinOnAction(ActionEvent actionEvent) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/register_form.fxml"));
        Scene scene = new Scene(registerRoot);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.show();
    }
}
