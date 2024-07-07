package lk.ijse.DTO;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddsDTO {
    private  String eq_id;
    private  String type;
    private  String description;
    private  double cost;
    private JFXButton btnRemove;
}
