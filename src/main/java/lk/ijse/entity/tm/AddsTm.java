package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AddsTm extends  CartTm{
    private  String eq_id;
    private  String type;
    private  String description;
    private  double cost;
   // private JFXButton btnRemove;


}
