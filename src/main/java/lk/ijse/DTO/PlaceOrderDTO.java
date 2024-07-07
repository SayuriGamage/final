package lk.ijse.DTO;

import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO {
    private Orders order;
    private List<OrderDetail> odList;



    }

