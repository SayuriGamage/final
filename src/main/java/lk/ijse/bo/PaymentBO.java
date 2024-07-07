package lk.ijse.bo;

import lk.ijse.DTO.PaymentDTO;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO {
      boolean savePayment(PaymentDTO paymentDTO) throws SQLException ;

      boolean updatePayment(PaymentDTO paymentDTO) throws SQLException ;

      boolean deletePayment(String paymentId) throws SQLException ;

     List<PaymentDTO> getAllPayment() throws SQLException ;

      PaymentDTO searchPayment(String id) throws SQLException ;

       String getCurrentPaymentId() throws SQLException ;
}
