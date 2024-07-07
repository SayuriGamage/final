package lk.ijse.bo.impl;

import lk.ijse.DTO.PaymentDTO;
import lk.ijse.bo.PaymentBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.dao.PaymentDAO;
import lk.ijse.entity.Payment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl  implements PaymentBO, SuperBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Payment);


    public  boolean savePayment(PaymentDTO payment) throws SQLException {

        return paymentDAO.save(new Payment(payment.getPay_id(),payment.getOr_id(),payment.getDate(),payment.getAmount()));
    }

    public  boolean updatePayment(PaymentDTO payment) throws SQLException {

        return paymentDAO.update(new Payment(payment.getPay_id(),payment.getOr_id(),payment.getDate(),payment.getAmount()));
    }

    public  boolean deletePayment(String paymentId) throws SQLException {

        return paymentDAO.delete(paymentId);
    }

    public List<PaymentDTO> getAllPayment() throws SQLException {
        ArrayList<Payment> payments= (ArrayList<Payment>) paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS=new ArrayList<>();
        for (Payment payment:payments){
            PaymentDTO paymentDTO=new PaymentDTO(payment.getPay_id(),payment.getOr_id(),payment.getDate(),payment.getAmount());
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;

    }

    public  PaymentDTO searchPayment(String id) throws SQLException {
        Payment payment=paymentDAO.search(id);
        return new PaymentDTO(payment.getPay_id(),payment.getOr_id(),payment.getDate(),payment.getAmount());
    }

    public   String getCurrentPaymentId() throws SQLException {
     return paymentDAO.getCurrentId();
    }
}
