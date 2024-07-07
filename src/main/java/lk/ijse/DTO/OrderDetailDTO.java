package lk.ijse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDTO {
    private String or_id;
    private  String sp_id;
    private  int qty;
    private  double cost;

}
