package lk.ijse.DTO;

public class EquipmentDTO {
    private  String  eq_id;
    private  String name;
    private String model;
    private  double cost;
    private  String purchase;
    private String warranty;
    private String manufacture;
    private  String user_id;

    @Override
    public String toString() {
        return "Equipment{" +
                "eq_id='" + eq_id + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", cost=" + cost +
                ", purchase='" + purchase + '\'' +
                ", warranty='" + warranty + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public String getEq_id() {
        return eq_id;
    }

    public void setEq_id(String eq_id) {
        this.eq_id = eq_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public EquipmentDTO(String eq_id, String name, String model, double cost, String purchase, String warranty, String manufacture, String user_id) {
        this.eq_id = eq_id;
        this.name = name;
        this.model = model;
        this.cost = cost;
        this.purchase = purchase;
        this.warranty = warranty;
        this.manufacture = manufacture;
        this.user_id = user_id;
    }

    public EquipmentDTO() {
    }
}
