package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.Maintenance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface MaintenanceDAO extends CrudDAO<Maintenance> {

    //  String getCurrentMaintenanceId() throws SQLException ;


     List<String> getMaintenanceIds() throws SQLException ;


   //   boolean savemaintenance(Maintenance maintenance) throws SQLException ;

     String getMaintenanceId( String description, double cost) throws SQLException ;

    String getMaintenanceempId(String description, double cost) throws SQLException;

}
