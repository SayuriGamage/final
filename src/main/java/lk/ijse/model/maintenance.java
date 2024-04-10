package lk.ijse.model;

public class maintenance {
    private String id;
    private String date;
    private double cost;
    private String description;

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public maintenance(String id, String date, double cost, String description) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    public maintenance() {
    }
}
