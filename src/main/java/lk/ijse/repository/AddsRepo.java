package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.tm.AddsTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddsRepo {

    public static List<AddsTm> getAllAddsTm() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT med.eq_id, med.type, m.description, m.cost\n" +
                "FROM maintenance_equipment_details med\n" +
                "JOIN maintenance m ON med.mm_id = m.mm_id;"; // Replace 'your_addstm_table' with your actual table name
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<AddsTm> addsTmList = new ArrayList<>();
        while (resultSet.next()) {
            String eq_id = resultSet.getString("eq_id");
            String type = resultSet.getString("type");
            String description = resultSet.getString("description");
            double cost = resultSet.getDouble("cost");

            // Create AddsTm object and add it to the list
            AddsTm addsTm = new AddsTm(eq_id, type, description, cost);
            addsTmList.add(addsTm);
        }


        return addsTmList;
    }
}
