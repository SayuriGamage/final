package lk.ijse.dao.Impl;


import lk.ijse.dao.MaintenanceDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Maintenance;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAOImpl implements MaintenanceDAO {


    public  String getCurrentId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT mm_id FROM maintenance ORDER BY mm_id DESC LIMIT 1");
        if (resultSet.next()) {
            String mainId = resultSet.getString(1);
            return mainId;
        }
        return null;
    }


    public Maintenance search(String od) throws SQLException {
        return null;
    }


    public  List<String> getMaintenanceIds() throws SQLException {


        List<String> eqList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT mm_id FROM maintenance");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            eqList.add(id);
        }
        return eqList;
    }


    public  boolean save(Maintenance maintenance) throws SQLException {

        return SQLUtil.execute("INSERT INTO maintenance VALUES(?, ?, ?,?,?)",maintenance.getMm_id(),maintenance.getDate(),maintenance.getDescription(),maintenance.getCost(),maintenance.getEmp_id());
    }


    public boolean delete(String id) throws SQLException {
        return false;
    }

    public boolean update(Maintenance dto) throws SQLException {
        return false;
    }


    public List<Maintenance> getAll() throws SQLException {
        return List.of();
    }


    public  String getMaintenanceId( String description, double cost) throws SQLException {
        String maintenanceId = null;
        String empId=null;

            try (ResultSet resultSet = SQLUtil.execute("SELECT mm_id FROM maintenance WHERE description = ? AND cost = ?",description,cost)) {
                if (resultSet.next()) {
                    maintenanceId = resultSet.getString("mm_id");

                }
            }


        return maintenanceId;
    }
    public  String getMaintenanceempId( String description, double cost) throws SQLException {

        String empId=null;

        try (ResultSet resultSet = SQLUtil.execute("SELECT emp_id FROM maintenance WHERE description = ? AND cost = ?",description,cost)) {
            if (resultSet.next()) {

                empId=resultSet.getString("emp_id");
            }
        }


        return empId;
    }


    }






