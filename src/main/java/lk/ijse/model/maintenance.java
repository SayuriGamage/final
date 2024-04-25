package lk.ijse.model;

public class maintenance {
    private  String mm_id;

    @Override
    public String toString() {
        return "maintenance{" +
                "mm_id='" + mm_id + '\'' +
                '}';
    }

    public String getMm_id() {
        return mm_id;
    }

    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }

    public maintenance(String mm_id) {
        this.mm_id = mm_id;
    }

    public maintenance() {
    }
}
