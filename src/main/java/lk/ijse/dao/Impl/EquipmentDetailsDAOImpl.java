package lk.ijse.dao.Impl;
import lk.ijse.dao.EquipmentDetailsDAO;
import lk.ijse.entity.EquipmentDetails;
import java.sql.SQLException;
import java.util.List;

public class EquipmentDetailsDAOImpl implements EquipmentDetailsDAO {

    public  boolean save(List<EquipmentDetails> osList) throws SQLException {
        for (EquipmentDetails os : osList) {
            if (SQLUtil.execute("INSERT INTO maintenance_equipment_details VALUES(?, ?, ?)",os.getType(),os.getMm_id(),os.getEq_id())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean save(EquipmentDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(EquipmentDetails dto) throws SQLException {
        return false;
    }

    @Override
    public List<EquipmentDetails> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getCurrentId() throws SQLException {
        return "";
    }

    @Override
    public EquipmentDetails search(String od) throws SQLException {
        return null;
    }
}
