package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EquipmentDAO extends CrudDAO<Equipment> {


     List<String> getEquipmentIds() throws SQLException ;

     int equipmentCount() throws SQLException ;
}
