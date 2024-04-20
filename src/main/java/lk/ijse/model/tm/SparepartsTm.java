package lk.ijse.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SparepartsTm {
    private String sp_id;



    private String name;
    private String manufacture;
    private double cost;
    private  int qty;
    private  String purchase;
    private  String mm_id;
}
