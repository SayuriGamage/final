package lk.ijse.model;

public class payment {
    private String id;
    private String date;
    private double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public payment(String id, String date, double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public payment() {
    }
}
