package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private static Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gdse69",
                "root",
                "Ijse@123"
        );
    }

    public static DbConnection getInstance() throws SQLException {
        if(dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }



    public static Connection getConnection() {
        return connection;
    }
}
