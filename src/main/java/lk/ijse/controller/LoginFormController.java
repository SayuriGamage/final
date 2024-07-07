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
import lk.ijse.bo.UserBO;
import lk.ijse.bo.impl.BOFactory;
import lk.ijse.bo.impl.BOTypes;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {


    public AnchorPane rootNode;
    public PasswordField uspasstext;
    public TextField texusen;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOTypes.userbo);

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
        String mail= userBO.checktests(pw);
      sendmail(mail);
    }

    private void sendmail(String email) throws SQLException {
        userBO.sendemils(email);
    }


    private void checkCredential(String username, String pw) throws SQLException, IOException {

            String db=userBO.Checkcredentialuser(username);
            if (db==null) {
                new Alert(Alert.AlertType.ERROR, "Sorry, user name not found!").show();
            }else {
            if (pw.equals(db)) {

                navigateDashboard(username);

            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry, the password is incorrect!").show();
            }
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