package lk.ijse.dao.Impl;

import lk.ijse.dao.CondemnedDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.Condemned;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondemnedDAOImpl implements CondemnedDAO {

    public  boolean save(Condemned condemned) throws SQLException {

        return SQLUtil.execute("INSERT INTO condemn VALUES(?, ?, ?, ?)",condemned.getC_id(),condemned.getDetails(),condemned.getDate(),condemned.getMm_id());
    }
    public  boolean delete(String id) throws SQLException {

        return  SQLUtil.execute("DELETE FROM condemn WHERE c_id = ?",id);
    }

    public  boolean update(Condemned condemned) throws SQLException {

        return SQLUtil.execute("UPDATE condemn SET details = ?, date = ?, mm_id=? WHERE c_id = ?",condemned.getDetails(),condemned.getDate(),condemned.getMm_id(),condemned.getC_id());
    }

    public  Condemned search(String id) throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT * FROM condemn WHERE c_id = ?",id);
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Condemned condemned = new Condemned(cus_id, name, address, tel);

            return condemned;
        }

        return null;
    }

    public  List<Condemned> getAll() throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT * FROM condemn");

        List<Condemned> conList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            Condemned condemned = new Condemned(id, name, address, tel);
            conList.add(condemned);
        }
        return conList;
    }

  /*  public static List<String> getIds() throws SQLException {
        String sql = "SELECT c_id FROM condemn";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }*/

    public  String getCurrentId() throws SQLException {

        ResultSet resultSet=SQLUtil.execute("SELECT c_id FROM condemn ORDER BY c_id DESC LIMIT 1");
        if (resultSet.next()) {
            String conId = resultSet.getString(1);
            return conId;
        }
        return null;

    }
}
