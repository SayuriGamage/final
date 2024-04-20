package lk.ijse.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String sp_id;
    private String name;
    private int qty;
    private double cost;
    private double total;
    private JFXButton btnRemove;
}
