package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import lk.ijse.email.GmailSender;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {


    public AnchorPane rootNode;

    public PasswordField uspasstext;
    public TextField texusen;



    public void loginAction(ActionEvent actionEvent) throws IOException {

       String username = texusen.getText();
       String pw = uspasstext.getText();

        try {
            checkCredential(username, pw);
            checktest(pw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checktest(String pw) throws SQLException {
        String sql = "select email from user where password=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, pw);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String emal=resultSet.getString("email");
            sendmail(emal);

        }
    }

    private void sendmail(String email) throws SQLException {
        String sql = "SELECT eq_id, name FROM equipment WHERE warranty = CURDATE()";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<String> ss = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("eq_id");
            String name=resultSet.getString("name");
            ss.add(id+":"+name);
        }

        GmailSender.setData(ss, email);
    }


    private void checkCredential(String username, String pw) throws SQLException, IOException {
        String sql = "select name,password from user where name=?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, username);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {

            String dbpw = resultSet.getString("password");
            if (pw.equals(dbpw)) {

                navigateDashboard(username);

            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry, the password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Sorry, user name not found!").show();
        }
    }





   private void navigateDashboard(String username) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard_form.fxml"));
       Parent dashboardRoot = loader.load();
       DashboardFormController controller = loader.getController();
      controller.setUsername(username); // Pass the username to the DashboardFormController
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        texusen.requestFocus();
        uspasstext.requestFocus();

    }

}