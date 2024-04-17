package lk.ijse.model.tm;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CondemnedTm {
    private  String c_id;

    private  String mm_id;
    private  String details;
    private String date;
}
