package lk.ijse.model;

public class Condemned {
        private String c_id;
        private String details;
        private String date;

private  String mm_id;

    @Override
    public String toString() {
        return "Condemned{" +
                "c_id='" + c_id + '\'' +
                ", details='" + details + '\'' +
                ", date='" + date + '\'' +
                ", mm_id='" + mm_id + '\'' +
                '}';
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMm_id() {
        return mm_id;
    }

    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }

    public Condemned(String c_id, String details, String date, String mm_id) {
        this.c_id = c_id;
        this.details = details;
        this.date = date;
        this.mm_id = mm_id;
    }

    public Condemned() {
    }
}
