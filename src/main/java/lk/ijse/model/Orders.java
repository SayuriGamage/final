package lk.ijse.model;

public class Orders {
    private  String or_id;

    @Override
    public String toString() {
        return "Orders{" +
                "or_id='" + or_id + '\'' +
                '}';
    }

    public String getOr_id() {
        return or_id;
    }

    public void setOr_id(String or_id) {
        this.or_id = or_id;
    }

    public Orders(String or_id) {
        this.or_id = or_id;
    }

    public Orders() {
    }
}
