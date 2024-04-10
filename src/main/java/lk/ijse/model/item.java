package lk.ijse.model;

public class item {
    private String id;
    private String name;
    private String model;
    private double cost;
    private String purchase;
    private String warranty;
    private String manufacture;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public item(String id, String name, String model, double cost, String purchase, String warranty, String manufacture) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.cost = cost;
        this.purchase = purchase;
        this.warranty = warranty;
        this.manufacture = manufacture;
    }



    public item(){

    }
}
