package lk.ijse.model;

public class employee {
    private String id;
    private String name;
    private String jobTitel;
    private String maintain_role;

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

    public String getJobTitel() {
        return jobTitel;
    }

    public void setJobTitel(String jobTitel) {
        this.jobTitel = jobTitel;
    }

    public String getMaintain_role() {
        return maintain_role;
    }

    public void setMaintain_role(String maintain_role) {
        this.maintain_role = maintain_role;
    }

    public employee(String id, String name, String jobTitel, String maintain_role) {
        this.id = id;
        this.name = name;
        this.jobTitel = jobTitel;
        this.maintain_role = maintain_role;
    }

    public employee() {
    }
}
