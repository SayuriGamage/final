package lk.ijse.bo.impl;
import lk.ijse.DTO.EquipmentDTO;
import lk.ijse.bo.EquipmentBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.EquipmentDAO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.dao.Impl.EquipmentDAOImpl;
import lk.ijse.entity.Equipment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBOImpl implements EquipmentBO , SuperBO {

    EquipmentDAO equipmentDAO= (EquipmentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Equipment);

    public  boolean saveEquipment(EquipmentDTO equipmentDTO) throws SQLException {

        return equipmentDAO.save(new Equipment(equipmentDTO.getEq_id(),equipmentDTO.getName(),equipmentDTO.getModel(),equipmentDTO.getCost(),equipmentDTO.getPurchase(),equipmentDTO.getWarranty(),equipmentDTO.getManufacture(),equipmentDTO.getUser_id()));
    }
    public  boolean updateEquipment(EquipmentDTO equipmentDTO) throws SQLException {

        return  equipmentDAO.update(new Equipment(equipmentDTO.getEq_id(),equipmentDTO.getName(),equipmentDTO.getModel(),equipmentDTO.getCost(),equipmentDTO.getPurchase(),equipmentDTO.getWarranty(),equipmentDTO.getManufacture(),equipmentDTO.getUser_id()));
    }

    public  boolean deleteEquipment(String id) throws SQLException {

        return equipmentDAO.delete(id);
    }
    public List<EquipmentDTO> getAllEquipment() throws SQLException {
        ArrayList<Equipment> equipments= (ArrayList<Equipment>) equipmentDAO.getAll();
        ArrayList<EquipmentDTO> equipmentDTOS=new ArrayList<>();
        for(Equipment equipment:equipments){
            EquipmentDTO equipmentDTO=new EquipmentDTO(equipment.getEq_id(),equipment.getName(),equipment.getModel(),equipment.getCost(),equipment.getPurchase(),equipment.getWarranty(),equipment.getManufacture(),equipment.getUser_id());
            equipmentDTOS.add(equipmentDTO);
        }
        return equipmentDTOS;
    }

    public  EquipmentDTO searchEquipment(String id) throws SQLException {
        Equipment equipment=equipmentDAO.search(id);
        return new EquipmentDTO(equipment.getEq_id(),equipment.getName(),equipment.getModel(),equipment.getCost(),equipment.getPurchase(),equipment.getWarranty(),equipment.getManufacture(),equipment.getUser_id());

    }

    public  String getCurrentEquipmentId() throws SQLException {

      return equipmentDAO.getCurrentId();
    }

    public int equipmentCount() throws SQLException {
      return equipmentDAO.equipmentCount();
    }

}
