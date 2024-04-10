package lk.ijse.model;

public class orders {
    private String id;
    private String delivaryDate;
    private String orderDate;
    private int qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelivaryDate() {
        return delivaryDate;
    }

    public void setDelivaryDate(String delivaryDate) {
        this.delivaryDate = delivaryDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public orders(String id, String delivaryDate, String orderDate, int qty) {
        this.id = id;
        this.delivaryDate = delivaryDate;
        this.orderDate = orderDate;
        this.qty = qty;
    }

    public orders() {
    }
}
