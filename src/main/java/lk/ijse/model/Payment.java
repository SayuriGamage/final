package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {
    private  String pay_id;
    private  String or_id;
    private  String date;
    private double amount;


}
