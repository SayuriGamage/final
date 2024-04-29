package lk.ijse.repository;

import com.mysql.cj.xdevapi.Table;
import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.model.PlaceOrder;
import lk.ijse.model.Spareparts;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrdersRepo.save(po.getOrder());

            if (isOrderSaved) {

                boolean isQtyUpdated = SparepartsRepo.update(po.getOdList());

                if (!isQtyUpdated) {
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());

                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
          //  new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
