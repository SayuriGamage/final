package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {

    public TextField reginametext;
    public TextField egiuseridtext;
    public TextField regipasswordtext;


    public void registerOnAction(ActionEvent actionEvent) {

        String userId = egiuseridtext.getText();
        String name = reginametext.getText();
        String password = regipasswordtext.getText();

        boolean isSaved = false;
        try {
            isSaved = saveUser(userId, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
        }
    }

    private boolean saveUser(String userId, String name, String password) throws SQLException {
        String sql = "INSERT INTO user VALUES(?, ?, ?)";




        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);
        pstm.setObject(2, name);
        pstm.setObject(3, password);

        return pstm.executeUpdate() > 0;
    }


}
