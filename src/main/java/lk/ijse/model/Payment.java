package lk.ijse.model;

public class Payment {
    private  String pay_id;
    private  String or_id;
    private  String date;
    private double amount;

    @Override
    public String toString() {
        return "Payment{" +
                "pay_id='" + pay_id + '\'' +
                ", or_id='" + or_id + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getOr_id() {
        return or_id;
    }

    public void setOr_id(String or_id) {
        this.or_id = or_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment(String pay_id, String or_id, String date, double amount) {
        this.pay_id = pay_id;
        this.or_id = or_id;
        this.date = date;
        this.amount = amount;
    }

    public Payment() {
    }
}
