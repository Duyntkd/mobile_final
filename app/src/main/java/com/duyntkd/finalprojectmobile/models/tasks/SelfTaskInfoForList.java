package com.duyntkd.finalprojectmobile.models.tasks;

public class SelfTaskInfoForList {
    private int id;
    private String title;
    private String endDate;
    private String status;

    public SelfTaskInfoForList(int id, String title, String endDate, String status) {
        this.id = id;
        this.title = title;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
