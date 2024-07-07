package lk.ijse.bo.impl;

import lk.ijse.DTO.SparepartsDTO;
import lk.ijse.bo.SparepartsBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.dao.SparepartsDAO;
import lk.ijse.entity.Spareparts;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  SparepartsBOImpl implements SparepartsBO , SuperBO {
    SparepartsDAO sparepartsDAO= (SparepartsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Spareparts);

    public  boolean saveParts(SparepartsDTO spareparts) throws SQLException{
        return sparepartsDAO.save(new Spareparts(spareparts.getSp_id(),spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id()));
    }

    public  boolean updateParts(SparepartsDTO spareparts) throws SQLException {

        return sparepartsDAO.update(new Spareparts(spareparts.getSp_id(),spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id()));
    }

    public  boolean deleteParts(String sp_id) throws SQLException {
        return sparepartsDAO.delete(sp_id);
    }

    public  SparepartsDTO getById(String sp_id) throws SQLException {
        Spareparts spareparts=sparepartsDAO.getById(sp_id);
        return  new SparepartsDTO(spareparts.getSp_id(),spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id());
    }

    public List<SparepartsDTO> getAllParts() throws SQLException {
        ArrayList<Spareparts> sparepartss= (ArrayList<Spareparts>) sparepartsDAO.getAll();
        ArrayList<SparepartsDTO> sparepartsDTOS=new ArrayList<>();
        for (Spareparts spareparts:sparepartss){
            SparepartsDTO sparepartsDTO=new SparepartsDTO(spareparts.getSp_id(),spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id()
            );
            sparepartsDTOS.add(sparepartsDTO);
        }
        return sparepartsDTOS;
    }

    public  String getCurrentPartsId() throws SQLException {
       return sparepartsDAO.getCurrentId();
    }

    public int sparepartsCount() throws SQLException {
        return sparepartsDAO.sparepartsCount();
    }
}
