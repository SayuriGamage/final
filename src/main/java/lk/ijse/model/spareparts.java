package lk.ijse.model;

public class spareparts {
    private String id;
    private String name;
    private int qty;
    private double cost;
    private String manufacture;
    private String purchase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public spareparts(String id, String name, int qty, double cost, String manufacture, String purchase) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.cost = cost;
        this.manufacture = manufacture;
        this.purchase = purchase;
    }

    public spareparts() {
    }
}
