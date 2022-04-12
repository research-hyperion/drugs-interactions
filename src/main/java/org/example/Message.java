package org.example;

public class Message {
    private String drug1DbId;
    private String drug2DbId;
    private boolean isDone;

    public Message(String drug1DbId,String drug2DbId,boolean isDone) {
        this.drug1DbId=drug1DbId;
        this.drug2DbId=drug2DbId;
        this.isDone=isDone;
    }

    public String getDrug2DbId() {
        return drug2DbId;
    }

    public void setDrug2DbId(String drug2DbId) {
        this.drug2DbId = drug2DbId;
    }

    public String getDrug1DbId() {
        return drug1DbId;
    }

    public void setDrug1DbId(String drug1DbId) {
        this.drug1DbId = drug1DbId;
    }

    public boolean isDone() {
        return isDone;
    }
}
