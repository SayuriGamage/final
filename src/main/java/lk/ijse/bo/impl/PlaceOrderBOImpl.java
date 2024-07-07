package lk.ijse.bo.impl;

import lk.ijse.DTO.PlaceOrderDTO;
import lk.ijse.DTO.SparepartsDTO;
import lk.ijse.DTO.SupplierDTO;
import lk.ijse.bo.PlaceOrderBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.Impl.*;
import lk.ijse.dao.OrderDetailDAO;
import lk.ijse.dao.OrdersDAO;
import lk.ijse.dao.SparepartsDAO;
import lk.ijse.dao.SupplierDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class PlaceOrderBOImpl implements PlaceOrderBO, SuperBO {
    OrdersDAO ordersDAO= (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Order);
      SparepartsDAO sparepartsDAO= (SparepartsDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Spareparts);
       SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Supplier);
       OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.OrederDetail);

    public  String getCurrentOrderId() throws SQLException {

       return ordersDAO.getCurrentId();
    }

    public  List<String> getSparepartsCodes() throws SQLException {

       return sparepartsDAO.getSparepartsCodes();
    }
    public  List<String> getTellsSupplier() throws SQLException {

 return supplierDAO.getTellsSupplier();
    }
    public SparepartsDTO searchParts(String code) throws SQLException {
        Spareparts spareparts=sparepartsDAO.search(code);
        return new SparepartsDTO(spareparts.getSp_id(),spareparts.getName(),spareparts.getManufacture(),spareparts.getCost(),spareparts.getQty(),spareparts.getPurchase(),spareparts.getMm_id());
    }


    public SupplierDTO searchSuppliertell(String tell) throws SQLException {
        Supplier supplier=supplierDAO.search(tell);
        return new SupplierDTO(supplier.getSup_id(),supplier.getName(),supplier.getTel());
    }

    public  boolean placeOrders(PlaceOrderDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {

            boolean isOrderSaved = ordersDAO.save(po.getOrder());

            if (isOrderSaved) {



                boolean isQtyUpdated = sparepartsDAO.updateqty(po.getOdList());

                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = orderDetailDAO.save(po.getOdList());

                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }

            connection.rollback();
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
