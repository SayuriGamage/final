package lk.ijse.bo.impl;

import lk.ijse.DTO.SupplierDTO;
import lk.ijse.bo.SuperBO;
import lk.ijse.bo.SupplierBO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.dao.SupplierDAO;
import lk.ijse.entity.Supplier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO, SuperBO {

    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Supplier);

    public  boolean saveSupplier(SupplierDTO supplier) throws SQLException{
        return supplierDAO.save(new Supplier(supplier.getSup_id(),supplier.getName(),supplier.getTel()));
    }

    public  boolean deleteSupplier(String id) throws SQLException {

        return supplierDAO.delete(id);
    }

    public  boolean updateSupplier(SupplierDTO supplier) throws SQLException {

        return  supplierDAO.update(new Supplier(supplier.getSup_id(),supplier.getName(),supplier.getTel()));
    }

    public List<SupplierDTO> getAllSupplier() throws SQLException {
        ArrayList<Supplier> suppliers= (ArrayList<Supplier>) supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS=new ArrayList<>();
        for (Supplier supplier:suppliers){
            SupplierDTO supplierDTO=new SupplierDTO(supplier.getSup_id(),supplier.getName(),supplier.getTel());
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }
    public  SupplierDTO searchSupplier(String tell) throws SQLException {
        Supplier supplier=supplierDAO.search(tell);
        return new SupplierDTO(supplier.getSup_id(),supplier.getName(),supplier.getTel());
    }


    public  String getCurrentSupplierId() throws SQLException {

        return supplierDAO.getCurrentId();
    }
}
