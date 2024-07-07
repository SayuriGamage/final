package lk.ijse.entity;

public class Spareparts {
    private String sp_id;
    private String name;
    private String manufacture;
    private double cost;
    private  int qty;
    private  String purchase;
    private  String mm_id;


    public Spareparts() {
    }

    public Spareparts(String sp_id, String name, String manufacture, double cost, int qty, String purchase, String mm_id) {
        this.sp_id = sp_id;
        this.name = name;
        this.manufacture = manufacture;
        this.cost = cost;
        this.qty = qty;
        this.purchase = purchase;
        this.mm_id = mm_id;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getMm_id() {
        return mm_id;
    }

    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }

    @Override
    public String toString() {
        return "Spareparts{" +
                "sp_id='" + sp_id + '\'' +
                ", name='" + name + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", cost=" + cost +
                ", qty=" + qty +
                ", purchase='" + purchase + '\'' +
                ", mm_id='" + mm_id + '\'' +
                '}';
    }
}
