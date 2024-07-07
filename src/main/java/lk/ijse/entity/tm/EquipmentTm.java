package lk.ijse.entity.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class EquipmentTm {
    private  String  eq_id;
    private  String name;
    private String model;
    private  double cost;
    private  String purchase;
    private String warranty;
    private String manufacture;
    private  String user_id;
}
