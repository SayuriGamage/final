package lk.ijse.bo;

import lk.ijse.DTO.PlaceOrderDTO;
import lk.ijse.DTO.SparepartsDTO;
import lk.ijse.DTO.SupplierDTO;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO {

      String getCurrentOrderId() throws SQLException ;

     List<String> getSparepartsCodes() throws SQLException ;

      List<String> getTellsSupplier() throws SQLException ;

     SparepartsDTO searchParts(String code) throws SQLException ;


     SupplierDTO searchSuppliertell(String tell) throws SQLException ;

    boolean placeOrders(PlaceOrderDTO po) throws SQLException ;
}
