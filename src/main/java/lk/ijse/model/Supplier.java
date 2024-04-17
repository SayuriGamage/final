package lk.ijse.model;

public class Supplier {
    private  String sup_id;
    private  String name;

    @Override
    public String toString() {
        return "Supplier{" +
                "sup_id='" + sup_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getSup_id() {
        return sup_id;
    }

    public void setSup_id(String sup_id) {
        this.sup_id = sup_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier(String sup_id, String name) {
        this.sup_id = sup_id;
        this.name = name;
    }

    public Supplier() {
    }
}
