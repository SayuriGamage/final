package lk.ijse.model;

public class condemin {
    private String id;
    private String date;
    private String details;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public condemin(String id, String date, String details) {
        this.id = id;
        this.date = date;
        this.details = details;
    }

    public condemin() {
    }
}
