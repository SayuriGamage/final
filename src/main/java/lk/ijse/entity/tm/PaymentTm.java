package lk.ijse.entity.tm;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class PaymentTm {

    private String pay_id;
    private String or_id;
    private String date;
    private double amount;
}