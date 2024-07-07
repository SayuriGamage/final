package lk.ijse.dao;

import lk.ijse.dao.Impl.SQLUtil;
import lk.ijse.entity.EquipmentDetails;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDetailsDAO extends CrudDAO<EquipmentDetails> {


     boolean save(List<EquipmentDetails> osList) throws SQLException ;
}




