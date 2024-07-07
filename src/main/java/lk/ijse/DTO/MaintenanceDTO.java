package lk.ijse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MaintenanceDTO {
    private  String mm_id;
private  String date;
private  String description;
private  double cost;
private  String emp_id;

}
