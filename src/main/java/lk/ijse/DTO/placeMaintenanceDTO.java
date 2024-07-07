package lk.ijse.DTO;

import lk.ijse.entity.EquipmentDetails;
import lk.ijse.entity.Maintenance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class placeMaintenanceDTO {
    private Maintenance maintenance;
    private List<EquipmentDetails> osList;
}
