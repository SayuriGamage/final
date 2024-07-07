package lk.ijse.bo;
import lk.ijse.DTO.SupplierDTO;
import java.sql.SQLException;
import java.util.List;

public interface SupplierBO {


      boolean saveSupplier(SupplierDTO supplier) throws SQLException ;

      boolean deleteSupplier(String id) throws SQLException ;

      boolean updateSupplier(SupplierDTO supplier) throws SQLException ;

     List<SupplierDTO> getAllSupplier() throws SQLException ;

      SupplierDTO searchSupplier(String tell) throws SQLException ;

      String getCurrentSupplierId() throws SQLException ;
}
