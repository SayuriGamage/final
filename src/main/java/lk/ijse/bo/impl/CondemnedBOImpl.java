package lk.ijse.bo.impl;
import lk.ijse.DTO.CondemnedDTO;
import lk.ijse.bo.CondemnedBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.CondemnedDAO;
import lk.ijse.dao.Impl.CondemnedDAOImpl;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.entity.Condemned;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondemnedBOImpl implements CondemnedBO, SuperBO {

    CondemnedDAO condemnedDAO= (CondemnedDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Condemned);

    public  boolean saveCondemn(CondemnedDTO condemnedDTO) throws SQLException {
        return condemnedDAO.save(new Condemned(condemnedDTO.getC_id(),condemnedDTO.getDetails(),condemnedDTO.getDate(),condemnedDTO.getMm_id()));
    }
    public  boolean deleteCondemn(String id) throws SQLException {

        return  condemnedDAO.delete(id);
    }

    public  boolean updateCondemn(CondemnedDTO condemnedDTO) throws SQLException {

        return condemnedDAO.update(new Condemned(condemnedDTO.getC_id(),condemnedDTO.getDetails(),condemnedDTO.getDate(),condemnedDTO.getMm_id()));
    }

    public  CondemnedDTO searchCondemn(String id) throws SQLException {
        Condemned condemned=condemnedDAO.search(id);
        return new CondemnedDTO(condemned.getC_id(),condemned.getDetails(),condemned.getDate(),condemned.getMm_id());
    }

    public List<CondemnedDTO> getAllCondemn() throws SQLException {

        ArrayList <Condemned> condemneds = (ArrayList<Condemned>) condemnedDAO.getAll();
        ArrayList<CondemnedDTO> condemnedDTOS=new ArrayList<>();
        for (Condemned condemned:condemneds){
            CondemnedDTO condemnedDTO=new CondemnedDTO(condemned.getC_id(),condemned.getDetails(),condemned.getDate(),condemned.getMm_id());
            condemnedDTOS.add(condemnedDTO);
        }
        return condemnedDTOS;
    }

    public  String getCurrentCondemnId() throws SQLException {
      return condemnedDAO.getCurrentId();

    }
}
