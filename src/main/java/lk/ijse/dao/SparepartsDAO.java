package lk.ijse.dao;


import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Spareparts;


import java.sql.SQLException;

import java.util.List;

public interface SparepartsDAO extends CrudDAO<Spareparts> {
    //  boolean savespareparts(Spareparts spareparts) throws SQLException;

   //   boolean updatespareparts(Spareparts spareparts) throws SQLException;

   //   boolean deletespareparts(String sp_id) throws SQLException ;

      Spareparts getById(String sp_id) throws SQLException ;

    // List<Spareparts> getAllspareparts() throws SQLException ;

      List<String> getSparepartsCodes() throws SQLException ;

   //   Spareparts searchBySparepartsId(String code) throws SQLException ;

      boolean updateqty(List<OrderDetail> odList) throws SQLException;

  //  String getCurrentsparepartsId() throws SQLException ;

     int sparepartsCount() throws SQLException ;
}
